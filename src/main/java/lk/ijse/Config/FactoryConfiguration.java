package lk.ijse.Config;

import lk.ijse.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfig;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
       /* Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Transactions.class);
        sessionFactory = configuration.buildSessionFactory();*/
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(Branch.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Transactions.class);

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

