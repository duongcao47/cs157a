package edu.sjsu.cs157a.DAOs;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sjsu.cs157a.models.Plane;

public class PlaneDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(Plane.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public Integer addPlane(Plane newPlane) { 
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer pID = null;
		
		try {
			tx = session.beginTransaction();
			pID = (Integer) session.save(newPlane);
			logger.info(newPlane + " has been added to Plane with pID=" + pID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return pID;
	}
	
	public Plane getPlane(String manufacturer, String model) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Plane p = null;
		
		try {
			tx = session.beginTransaction();
			NaturalIdLoadAccess<Plane> naturalPlaneIdentifier = session.byNaturalId(Plane.class);
			naturalPlaneIdentifier.using("manufacturer", manufacturer);
			naturalPlaneIdentifier.using("model", model);
			p = naturalPlaneIdentifier.load();
			logger.info(p + " has been added to the database");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return p;
	}
	
	public Plane getPlane(Integer pID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Plane p = null;
		
		try {
			tx = session.beginTransaction();
			p = (Plane) session.get(Plane.class, pID);
			logger.info(p + " has been added to the database");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return p;
	}
	
	public List<Plane> getAllPlanes() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Plane> planes = null;
		
		try {
			tx = session.beginTransaction();
			planes = session.createQuery("FROM Plane").list();
			logger.info("All planes have been retrieved from the database");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return planes;
	}
	
	public void updatePlane(Integer pID, int capacity)  {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Plane p = session.get(Plane.class, pID);
			p.setCapacity(capacity);
			session.update(p);
			logger.info(p + " has had its capacity updated to " + capacity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
	public void removePlane(String manufacturer, String model) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			NaturalIdLoadAccess<Plane> naturalPlaneIdentifier = session.byNaturalId(Plane.class);
			naturalPlaneIdentifier.using("manufacturer", manufacturer);
			naturalPlaneIdentifier.using("model", model);
			Plane p = naturalPlaneIdentifier.load();
			logger.info(p + " has been removed from the databse.");
			session.delete(p);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

}
