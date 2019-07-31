package edu.sjsu.cs157a.DAOs;

import static org.junit.Assert.*;

import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.sjsu.cs157a.models.Flight;

public class FlightDAOTest extends BaseTest {
	
	private static FlightDAO flightDAO;
	
	@BeforeClass
	public static void init() {
		try {
			initConnectionAndDatabase();
			flightDAO = new FlightDAO();
			flightDAO.setSessionFactory(new Configuration().configure("devHibernate.cfg.xml").buildSessionFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAirlineTest() {
		Flight flight = flightDAO.getFlight(100);
		System.out.println(flight);
	}


}
