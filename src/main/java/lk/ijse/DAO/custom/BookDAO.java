package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Book;

public interface BookDAO extends CrudDAO<Book,String> {
    String getNextId();
}
