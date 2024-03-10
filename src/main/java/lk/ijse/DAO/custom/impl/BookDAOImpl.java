package lk.ijse.DAO.custom.impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DAO.custom.BookDAO;
import lk.ijse.Entity.Book;
import org.hibernate.Session;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    Session session = FactoryConfiguration.getInstance().getSession();

    @Override
    public boolean save(Book dto) {
        return false;
    }

    @Override
    public boolean update(Book dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getItem(String id) {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }
}
