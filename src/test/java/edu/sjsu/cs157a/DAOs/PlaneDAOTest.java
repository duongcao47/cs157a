package edu.sjsu.cs157a.DAOs;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.sjsu.cs157a.models.Plane;

public class PlaneDAOTest extends BaseTest {
	
private static PlaneDAO planeDAO;
	
	@BeforeClass
	public static void init() {
		try {
			initConnectionAndDatabase();
			planeDAO = new PlaneDAO();
			planeDAO.setSessionFactory(new Configuration().configure("devHibernate.cfg.xml").buildSessionFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void addPlaneTest() {
		Plane newPlane = new Plane("Riley", "F-18", 2);
		Integer pID = planeDAO.addPlane(newPlane);
		assert pID != null;
	}
	
	@Test
	public void getPlaneTest() {
		Plane p = planeDAO.getPlane("Boeing", "777");
		assert p.getManufacturer().equals("Boeing") && p.getModel().equals("777");
	}
	
	@Test
	public void getAllPlanesTest() {
		ArrayList<Plane> planes = (ArrayList<Plane>) planeDAO.getAllPlanes();
		assert planes.size() == 10;
	}
	
	@Test
	public void updatePlaneTest() {
		Plane p = planeDAO.getPlane("Boeing", "777");
		int oldCapacity = p.getCapacity();
		planeDAO.updatePlane(p.getpID(), oldCapacity + 1);
		p = planeDAO.getPlane("Boeing", "777");
		int newCapacity = p.getCapacity();
		assert oldCapacity == newCapacity-1;
	}
	
	@Test 
	public void removePlaneTest() {
		planeDAO.removePlane("Airbus", "A220");
		Plane deletedPlane = planeDAO.getPlane("Airbus", "A220");
		assert deletedPlane == null;
	}

}
