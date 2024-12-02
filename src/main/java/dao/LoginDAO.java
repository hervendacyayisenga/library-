package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import hibernate.Hibernatecfg;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LoginDAO {

    // Method to verify login credentials
	// Method to verify login credentials
	public User authenticate(String username, String password, String role) {
	    Session session = Hibernatecfg.getSessionFactory().openSession();
	    Transaction transaction = null;
	    User personUser = null;

	    try {
	        transaction = session.beginTransaction();
	        
	        // Assuming the password is hashed, ensure you hash the input password
	        String hashedPassword = hashPassword(password); // Implement your hashing logic here

	        // Query to authenticate the user
	        Query<User> query = session.createQuery("FROM PersonUser WHERE username = :username", User.class);
	        query.setParameter("username", username);
	        query.setParameter("password", hashedPassword); // Use the hashed password
	        query.setParameter("role", role);

	        // Get a unique result
	        personUser = query.uniqueResult();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace(); // Log the exception for debugging
	    } finally {
	        session.close();
	    }

	    return personUser; // This will be null if authentication fails
	}



    // Helper method to hash passwords
    private String hashPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }
}
