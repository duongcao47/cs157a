package edu.sjsu.cs157a.DAOs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sjsu.cs157a.models.Airport;
import edu.sjsu.cs157a.models.Flight;

public class FlightDAO {
	private static final Logger logger = LoggerFactory.getLogger(FlightDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public Integer addFlight(Flight newFlight) {
		
		return null;
	}
	
	public Flight getFlight(Integer flID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Flight flight = null;
		
		try {
			tx = session.beginTransaction();
			flight = (Flight) session.get(Flight.class, flID);
			logger.info(flight + " has been retrieved.");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return flight;
	}
}
