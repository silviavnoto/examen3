package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Habitacion;
import com.hibernate.util.HibernateUtil;

public class HabitacionDAO {

	public void insertHabitacion(Habitacion h) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(h);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void updateHabitacion(Habitacion h) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(h);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void deleteHabitacion(int idHabitacion) {
		Transaction transaction = null;
		Habitacion h = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			h = session.get(Habitacion.class, idHabitacion);
			session.remove(h);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public Habitacion selectHabitacionbyId(int idHabitacion) {
		Transaction transaction = null;
		Habitacion h = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			h = session.get(Habitacion.class, idHabitacion);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return h;

	}

	public List<Habitacion> selectAllHabitacion() {
		Transaction transaction = null;
		List<Habitacion> habitaciones = null;
		Habitacion h = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			habitaciones = session.createQuery("from Habitacion", Habitacion.class).getResultList();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return habitaciones;

	}

}
