package com.hibernate.util;

import java.util.Properties;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.model.Habitacion;
import com.hibernate.model.Servicio;

import org.hibernate.SessionFactory;


public class HibernateUtil {

private static SessionFactory sessionFactory;

public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
		try {
			Configuration configuration = new Configuration();
			
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			settings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3307/hotel?useSSL=false");
			settings.put(Environment.USER, "alumno");
			settings.put(Environment.PASS, "alumno");
			settings.put(Environment.SHOW_SQL, "false");
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			settings.put(Environment.HBM2DDL_AUTO, "update");
			
			configuration.setProperties(settings);
			

			configuration.addAnnotatedClass(Habitacion.class);

			configuration.addAnnotatedClass(Servicio.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return sessionFactory;
}


}
