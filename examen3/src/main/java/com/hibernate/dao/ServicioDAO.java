package com.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Servicio;
import com.hibernate.util.HibernateUtil;

public class ServicioDAO {

	public void insertServicio(Servicio s) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public void updateServicio(Servicio s) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
			transaction=session.beginTransaction();
			session.merge(s);
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public void deleteServicio(int idServicio) {
		Transaction transaction=null;
		Servicio s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
			transaction=session.beginTransaction();
			s=session.get(Servicio.class, idServicio);
			session.remove(s);
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	
	public Servicio selectServiciobyId(int idServicio) {
		Transaction transaction=null;
		Servicio s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
			transaction=session.beginTransaction();
			s=session.get(Servicio.class, idServicio);
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return s;
	
	}
	
	public List<Servicio> selectAllServicio(){
		Transaction transaction=null;
		List<Servicio> servicios =null;
		Servicio s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
			transaction=session.beginTransaction();
			servicios=session.createQuery("from Servicio",Servicio.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return servicios;
	
		
	}
	public int insertServicioAndGetId(Servicio servicio) {
        Session session = null;
        Transaction transaction = null;
        int generatedId = -1; // Inicializar el ID generado

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Guardar el cliente en la base de datos
            session.save(servicio);
            // Obtener el ID generado automáticamente
            generatedId = servicio.getIdServicio();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return generatedId; 
    }
	}
