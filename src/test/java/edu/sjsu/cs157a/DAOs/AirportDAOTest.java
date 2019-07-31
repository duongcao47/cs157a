package edu.sjsu.cs157a.DAOs;

import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.sjsu.cs157a.models.Airport;

public class AirportDAOTest extends BaseTest {

private static AirportDAO airportDAO;
	
	@BeforeClass
	public static void init() {
		try {
			initConnectionAndDatabase();
			airportDAO = new AirportDAO();
			airportDAO.setSessionFactory(new Configuration().configure("devHibernate.cfg.xml").buildSessionFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAirlineTest() {
		Airport airport = airportDAO.getAirport("Chico Municipal Airport");
		System.out.println(airport);
	}
	
}
