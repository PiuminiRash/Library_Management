package lk.ijse.DAO.custom.impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = new ArrayList<>();

        userList.add(dto);
        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
//        List<User> userList = new ArrayList<>();

        session.update(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User customer = session.get(User.class,id);
        session.delete(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("FROM User").list();
    }

    @Override
    public User getItem(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class,id);
            transaction.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String newId = "C000";
        Transaction transaction = session.beginTransaction();
        List list = session.createNativeQuery("select user_id from user order by user_id desc limit 1").list();
        if (!list.isEmpty()) newId = (String) list.get(0);
        transaction.commit();
        session.close();
        return newId;
    }
}
