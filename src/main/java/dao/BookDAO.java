package dao;

import hibernate.Hibernatecfg;
import model.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAO {
    public void save(Book book) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            System.out.println("Book saved successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Failed to save book: " + e.getMessage());
            e.printStackTrace();
        }
}


    public void update(Book book) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(Long bookId) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            if (book != null) {
                session.delete(book);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Book findById(Long bookId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Book.class, bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Book", Book.class).list();
        } catch (Exception e) {
            System.err.println("Failed to retrieve books: " + e.getMessage());
            return null;
        }
    }


    public Book getBookById(Long bookId) {
        Transaction transaction = null;
        Book book = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            // Create a query to fetch the book by bookId
            Query<Book> query = session.createQuery("FROM Book WHERE book_id = :bookId", Book.class);
            query.setParameter("bookId", bookId);
            book = query.uniqueResult(); // This returns a single result or null if not found
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return book;
    }
}
