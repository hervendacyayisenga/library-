package dao;

import model.MembershipTypeBook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class MembershipTypeBookDAO {

    // Save a new MembershipTypeBook
    public void save(MembershipTypeBook membershipTypeBook) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(membershipTypeBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Update an existing MembershipTypeBook
    public void update(MembershipTypeBook membershipTypeBook) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(membershipTypeBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete a MembershipTypeBook by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MembershipTypeBook membershipTypeBook = session.get(MembershipTypeBook.class, id);
            if (membershipTypeBook != null) {
                session.delete(membershipTypeBook);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Find a MembershipTypeBook by ID
    public MembershipTypeBook findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(MembershipTypeBook.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve all MembershipTypeBook records
    public List<MembershipTypeBook> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("from MembershipTypeBook", MembershipTypeBook.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
