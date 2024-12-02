package dao;

import model.Shelf;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class ShelfDAO {

    // Save a new shelf
    public void save(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(shelf);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Find a shelf by ID
    public Shelf findById(Long shelfId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Shelf.class, shelfId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all shelves
    @SuppressWarnings("unchecked")
    public List<Shelf> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("from Shelf").list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing shelf
    public void update(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shelf);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete a shelf by ID
    public void delete(Long shelfId) {
        Transaction transaction = null;
        try (Session session =Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Shelf shelf = session.get(Shelf.class, shelfId);
            if (shelf != null) {
                session.delete(shelf);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
