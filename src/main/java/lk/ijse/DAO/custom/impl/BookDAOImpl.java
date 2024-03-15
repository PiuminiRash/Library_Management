package lk.ijse.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DAO.custom.BookDAO;
import lk.ijse.Entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    Session session = FactoryConfiguration.getInstance().getSession();

    @Override
    public boolean save(Book dto) {
       try {
           Transaction transaction = session.beginTransaction();
           Serializable save = (Serializable) session.save(dto);
           transaction.commit();
           return save!=null;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       } finally {
           session.close();
       }
    }

    @Override
    public boolean update(Book dto) {
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
            Book book = session.get(Book.class,id);
            session.delete(book);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAll() {
       return session.createQuery("FROM Book").list();
    }

    @Override
//    public Book getItem(String id) {
//        try {
//            Transaction transaction = session.beginTransaction();
//            Book book = session.get(Book.class,id);
//            transaction.commit();
//            return book;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
    public Book getItem(String id) {
        try {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            transaction.commit();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public String getNextId() {
        try {
            String newId = "B000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select book_id from book order by book_id desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
          return newId;
        } catch (HibernateException e) {
             e.printStackTrace();
            return null;
        }
    }
}
