package lk.ijse.DAO.custom.impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(User dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getItem(String id) {
        return null;
    }
}
