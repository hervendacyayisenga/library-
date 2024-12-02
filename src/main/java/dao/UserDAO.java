package dao;

import hibernate.Hibernatecfg;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class UserDAO {

    // Helper method to hash password using SHA-256
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public void save(User personUser) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            personUser.setPassword(hashPassword(personUser.getPassword()));  // Hash password before saving
            session.persist(personUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    // Update an existing PersonUser with hashed password
    public void update(User personUser) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (personUser.getPassword() != null && !personUser.getPassword().isEmpty()) {
                personUser.setPassword(hashPassword(personUser.getPassword()));  // Hash new password if provided
            }
            session.update(personUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

	/*
	 * // Find a PersonUser by ID
	 * 
	 * @SuppressWarnings("deprecation") public User findById(Long id) { try (Session
	 * session = Hibernatecfg.getSessionFactory().openSession()) { return
	 * session.get(User.class, id); } catch (Exception e) { e.printStackTrace();
	 * return null; } }
	 */

    // Find a PersonUser by username
    public User findByUsername(String username) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                          .setParameter("username", username)
                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find all PersonUsers
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
        	return session.createQuery("SELECT p FROM PersonUser p", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Delete a PersonUser by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User personUser = session.get(User.class, id);
            if (personUser != null) {
                session.delete(personUser);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

	/*
	 * public User getUserById(String userId) { Transaction transaction = null; User
	 * user = null; try (Session session =
	 * Hibernatecfg.getSessionFactory().openSession()) { transaction =
	 * session.beginTransaction(); user = session.get(User.class, userId); // Adjust
	 * if the ID type is not String transaction.commit(); } catch (Exception e) { if
	 * (transaction != null) transaction.rollback(); e.printStackTrace(); } return
	 * user; }
	 */
    public User getUserById(String userId) {
        Transaction transaction = null;
        User user = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            // Create a query to fetch the user by userId
            Query<User> query = session.createQuery("FROM User WHERE user_id = :userId", User.class);
            query.setParameter("userId", userId);
            user = query.uniqueResult(); // This returns a single result or null if not found
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return user;
    }
}
