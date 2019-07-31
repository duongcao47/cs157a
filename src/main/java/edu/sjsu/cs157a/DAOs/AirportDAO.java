package edu.sjsu.cs157a.DAOs;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sjsu.cs157a.models.Airport;

public class AirportDAO {

	private static final Logger logger = LoggerFactory.getLogger(AirportDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public Integer addAirport(Airport newAirport) {

		return null;
	}

	public Airport getAirport(String name) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Airport airport = null;
		
		try {
			tx = session.beginTransaction();
			NaturalIdLoadAccess<Airport> naturalIdentifier = session.byNaturalId(Airport.class);
			naturalIdentifier.using("name", name);
			airport = (Airport)naturalIdentifier.load();
			logger.info(airport.getName() + " has been retrieved.");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return airport;
	}

	public Airport getAirport(Integer apID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Airport airport = null;
		
		try {
			tx = session.beginTransaction();
			airport = (Airport) session.get(Airport.class, apID);
			logger.info(airport.getName() + " has been retrieved.");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return airport;
	}
	
	public void updateAirport() {
		
	}
	
	public void removeAirport() {
		
	}
}
