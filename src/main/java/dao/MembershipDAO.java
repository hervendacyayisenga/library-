package dao;

import model.Membership;
import model.User;
import model.MembershipTypeBook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class MembershipDAO {

    // Save a new membership
    public void save(Membership membership) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Find user by ID
    public User findUserById(Long userId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(User.class, userId); // Fetch user by ID
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find membership type by name
    public MembershipTypeBook findMembershipTypeByName(String membershipTypeName) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM MembershipTypeBook m WHERE m.typeName = :typeName", MembershipTypeBook.class)
                    .setParameter("typeName", membershipTypeName)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle not found scenario
        }
    }

    // Update an existing membership
    public void update(Membership membership) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Find membership by ID
    public Membership findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Membership.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Delete a membership by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Membership membership = session.get(Membership.class, id);
            if (membership != null) {
                session.delete(membership);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Get all memberships
    public List<Membership> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Membership", Membership.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
