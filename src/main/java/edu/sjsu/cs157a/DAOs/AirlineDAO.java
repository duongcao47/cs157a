package edu.sjsu.cs157a.DAOs;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sjsu.cs157a.models.Airline;
import edu.sjsu.cs157a.models.Plane;

public class AirlineDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(AirlineDAO.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public Integer addAirline(Airline newAirline) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer alID = null;
		
		try {
			tx = session.beginTransaction();
			alID = (Integer) session.save(newAirline);
			logger.info(newAirline + " has been added with alID of " + alID + ".");
			tx.commit();
		} catch(HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return alID;
	}
	
	public Integer addPlaneToFleet(Integer alID, Integer pID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Plane p = null;
		Airline airline = null;
		
		try {
			tx = session.beginTransaction();
			// get plane 
			p = (Plane)session.get(Plane.class, pID);
			
			// get airline 
			airline = (Airline)session.get(Airline.class, pID);
			
			// add to fleet
			airline.addToFleet(p);
			logger.info(p + " has been add to " + airline.getCompanyName());
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return p == null ? null : p.getpID();
	}
	
	public Integer addPlaneToFleet(String companyName, String manufacturer, String model) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Plane p = null;
		Airline airline = null;
		
		try {
			tx = session.beginTransaction();
			// get plane 
			NaturalIdLoadAccess<Plane> naturalPlaneIdentifier = session.byNaturalId(Plane.class);
			naturalPlaneIdentifier.using("manufacturer", manufacturer);
			naturalPlaneIdentifier.using("model", model);
			p = (Plane)naturalPlaneIdentifier.load();
			
			// get airline 
			NaturalIdLoadAccess<Airline> naturalAirlineIdentifier = session.byNaturalId(Airline.class);
			naturalAirlineIdentifier.using("companyName", companyName);
			airline = (Airline)naturalAirlineIdentifier.load();
			
			// add to fleet
			airline.addToFleet(p);
			logger.info(p + " has been add to " + airline.getCompanyName());
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return p == null ? null : p.getpID();
	}
	
	public Airline getAirline(Integer alID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Airline airline = null;
		
		try {
			tx = session.beginTransaction();
			airline = (Airline) session.get(Airline.class, alID);
			Hibernate.initialize(airline.getFleet());
			logger.info(airline.getCompanyName() + " has been retrieved.");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return airline;
	}
	
	public Airline getAirline(String companyName) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Airline airline = null;
		
		try {
			tx = session.beginTransaction();
			NaturalIdLoadAccess<Airline> naturalIdentifier = session.byNaturalId(Airline.class);
			naturalIdentifier.using("companyName", companyName);
			airline = (Airline)naturalIdentifier.load();
			Hibernate.initialize(airline.getFleet());
			logger.info(airline.getCompanyName() + " has been retrieved.");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return airline;
	}
	
}
