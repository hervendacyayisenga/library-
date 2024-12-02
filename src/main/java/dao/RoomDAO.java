package dao;

import model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class RoomDAO {

    // Save a new room
    public void save(Room room) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(room);
            transaction.commit();
            System.out.println("Room saved successfully: " + room.getRoomName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to save room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update room
    public void update(Room room) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
            System.out.println("Room updated successfully: " + room.getRoomName());
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Failed to update room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete room
    public void delete(Long roomId) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Room room = session.get(Room.class, roomId);
            if (room != null) {
                session.delete(room);
                System.out.println("Room deleted successfully: " + room.getRoomName());
            } else {
                System.out.println("Room not found for deletion: ID = " + roomId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to delete room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Find all rooms
    public List<Room> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        } catch (Exception e) {
            System.err.println("Failed to retrieve rooms: " + e.getMessage());
            return null;
        }
    }

    // Find room by ID
    public Room findById(Long roomId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Room.class, roomId);
        } catch (Exception e) {
            System.err.println("Failed to find room: " + e.getMessage());
            return null;
        }
    }
}
