package lk.ijse.Config;

import lk.ijse.Entity.Book;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Transactions;
import lk.ijse.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfig;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Transactions.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if(factoryConfig == null) {
            factoryConfig = new FactoryConfiguration();
        }
        return factoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

