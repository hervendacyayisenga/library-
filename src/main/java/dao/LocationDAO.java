package dao;

import hibernate.Hibernatecfg;
import model.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LocationDAO {

    // Save a new location
    public void save(Location location) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Update an existing location
    public void update(Location location) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Find location by ID
    public Location findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Location.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Delete a location by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Location location = session.get(Location.class, id);
            if (location != null) {
                session.delete(location);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Find all locations
    public List<Location> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("from Location", Location.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 // Find a PersonUser by userId
    @SuppressWarnings("deprecation")
    public Location findByUserId(String userId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            String hql = "FROM Location WHERE userId = :userId";
            return session.createQuery(hql, Location.class)
                          .setParameter("userId", userId)
                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 // In LocationDAO.java
    public Location findByLocationCode(String locationCode) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Location WHERE locationCode = :locationCode", Location.class)
                    .setParameter("locationCode", locationCode)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
