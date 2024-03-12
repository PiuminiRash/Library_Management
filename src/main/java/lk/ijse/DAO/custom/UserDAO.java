package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.User;

public interface UserDAO extends CrudDAO<User,String> {
    String getNextId();
}
