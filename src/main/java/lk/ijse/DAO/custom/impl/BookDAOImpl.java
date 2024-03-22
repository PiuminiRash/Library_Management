package lk.ijse.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DAO.custom.BookDAO;
import lk.ijse.Entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> bookList = new ArrayList<>();

        bookList.add(dto);
        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Book dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(dto);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class,id);
        session.delete(book);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Book> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("FROM Book").list();
    }

    @Override
    public Book getItem(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class,id);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String newId = "B000";
        Transaction transaction = session.beginTransaction();
        List list = session.createNativeQuery("select book_id from book order by book_id desc limit 1").list();
        if (!list.isEmpty()) newId = (String) list.get(0);
        transaction.commit();
        return newId;
    }
}
