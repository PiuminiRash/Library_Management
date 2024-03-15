package lk.ijse.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.Entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    Session session = FactoryConfiguration.getInstance().getSession();
    @Override
    public boolean save(User dto) {
        try {
            Transaction transaction = session.beginTransaction();
            Serializable save = (Serializable) session.save(dto);
            transaction.commit();
            return save != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(User dto) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
       try {
           Transaction transaction = session.beginTransaction();
           User customer = session.get(User.class,id);
           session.delete(customer);
           transaction.commit();
           return true;
       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
           return false;
       } finally {
           session.close();
       }
    }

    @Override
    public List<User> getAll() {
        return session.createQuery("FROM User").list();
    }

    @Override
    public User getItem(String id) {
        try {
            Transaction transaction = session.beginTransaction();
            User customer = session.get(User.class,id);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public String getNextId() {
        try {
            String newId = "C000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select user_id from user order by user_id desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
