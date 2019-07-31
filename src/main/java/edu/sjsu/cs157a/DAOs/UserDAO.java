package edu.sjsu.cs157a.DAOs;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.sasl.AuthenticationException;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sjsu.cs157a.models.Airline;
import edu.sjsu.cs157a.models.Flight;
import edu.sjsu.cs157a.models.Plane;
import edu.sjsu.cs157a.models.User;

public class UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public Integer addUser(User newUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer uID = null;

		try {
			tx = session.beginTransaction();
			newUser.setPassword(hashPassword(newUser.getPassword()));
			uID = (Integer) session.save(newUser);
			logger.info(newUser + " has been registered.");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}

		return uID;
	}

	public User getUser(String email, String password) throws AuthenticationException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User authenticatedUser = null;

		try {
			tx = session.beginTransaction();
			if ((authenticatedUser = isUserEmail(session, email)) == null) {
				throw new AuthenticationException("The email " + email + " is not registered with our service.");
			} else if (!authenticatedUser.getPassword().equals(hashPassword(password))) {
				throw new AuthenticationException("Username or password is incorrect.");
			}
			logger.info(authenticatedUser + " has been retrieved.");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}

		return authenticatedUser;
	}

	public void removeUser(Integer uID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, uID);
			session.delete(user);
			logger.info(user + " has been deleted.");
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

	private String hashPassword(String unhashedPassword) {
		String salt1 = "$4J", salt2 = "M%a";
		return DigestUtils.sha256Hex(salt1 + unhashedPassword + salt2);
	}

	private User isUserEmail(Session session, String email) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);
		Root<User> root = cr.from(User.class);
		cr.select(root).where(cb.equal(root.get("email"), email));
		Query<User> query = session.createQuery(cr);
		List<User> result = query.getResultList();
		for (User u : result) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
	}

	public void addTrip(Integer uID, Integer fID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			// get user
			User user = (User)session.get(User.class, uID);
			
			// get flight 
			Flight flight = (Flight)session.get(Flight.class, fID);
		
			// add to trip
			user.getTrips().add(flight);
			
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
