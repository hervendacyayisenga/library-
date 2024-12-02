package dao;

import hibernate.Hibernatecfg;
import model.Borrower;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowerDAO {

	public void saveBorrower(Borrower borrower) {
	    Transaction transaction = null;
	    try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.persist(borrower);
	        transaction.commit();
	    } catch (HibernateException e) {
	        if (transaction != null) transaction.rollback();
	        System.err.println("Hibernate exception occurred: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        System.err.println("General exception occurred: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


    // Update an existing borrower
    public void update(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(borrower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Find a borrower by ID
    public Borrower findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Borrower.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find all borrowers
    @SuppressWarnings("unchecked")
    public List<Borrower> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Borrower").list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Delete a borrower by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Borrower borrower = session.get(Borrower.class, id);
            if (borrower != null) {
                session.delete(borrower);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
