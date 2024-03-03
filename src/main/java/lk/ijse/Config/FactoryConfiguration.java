package lk.ijse.Config;

import lk.ijse.Entity.Book;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Transactions;
import lk.ijse.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfig;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.Properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configuration
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Transactions.class)
                .addAnnotatedClass(User.class);
        sessionFactory=configuration.setProperties(properties).buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (null == factoryConfig) ? factoryConfig = new FactoryConfiguration() : factoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
