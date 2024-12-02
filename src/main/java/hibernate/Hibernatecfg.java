package hibernate;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import model.Borrower;
import model.Location;
import model.Membership;
import model.MembershipTypeBook;
import model.Room;
import model.Shelf;
import model.User;
import model.Book;

public class Hibernatecfg {
    
    private static SessionFactory sessionFactory;

    private Hibernatecfg() {
        // Private constructor to prevent instantiation
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/webtesting");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "2020pass@");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                
                settings.put(Environment.HBM2DDL_AUTO, "update");  // Use 'update' in production
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.USE_SQL_COMMENTS, "true");
                settings.put(Environment.GENERATE_STATISTICS, "true");
                settings.put(Environment.C3P0_MIN_SIZE, 5);           
                settings.put(Environment.C3P0_MAX_SIZE, 20);           
                settings.put(Environment.C3P0_ACQUIRE_INCREMENT, 1);   
                settings.put(Environment.C3P0_TIMEOUT, 1800);          
                settings.put(Environment.C3P0_MAX_STATEMENTS, 150);    

                configuration.setProperties(settings);

                // Add annotated classes
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Borrower.class);
                configuration.addAnnotatedClass(Membership.class);
                configuration.addAnnotatedClass(MembershipTypeBook.class);
                configuration.addAnnotatedClass(Room.class);
                configuration.addAnnotatedClass(Shelf.class);
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Location.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                System.out.println("Hibernate Java Config serviceRegistry created");

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                
            } catch (Exception e) {
                System.err.println("SessionFactory creation failed");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
