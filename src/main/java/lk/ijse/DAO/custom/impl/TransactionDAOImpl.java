package lk.ijse.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DAO.custom.TransactionDAO;
import lk.ijse.Entity.Book;
import lk.ijse.Entity.Transactions;
import lk.ijse.Entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public boolean save(Transactions dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
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
    public boolean update(Transactions dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
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
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Transactions transactions = session.get(Transactions.class,id);
            session.delete(transactions);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Transactions> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("FROM Transactions").list();
    }

    @Override
    public Transactions getItem(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Transactions transactions = session.get(Transactions.class,id);
            transaction.commit();
            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String newId = "TRS-000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select res_id from reservation order by res_id desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveTrans(Transactions transactionsEntity, Book bookEntity, User userEntity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        String status = "Not Available";
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            List<Transactions> transactions = new ArrayList<>();

            transactionsEntity.setUserList(userEntity);
            userEntity.setTransaction(transactions);

            transactionsEntity.setBookList(bookEntity);
            bookEntity.setTransactions(transactions);

            transactions.add(transactionsEntity);
            session.save(transactionsEntity);

            session.createQuery("UPDATE Book b SET b.status = :status WHERE b.id = : book_id")
                    .setParameter("status",status).setParameter("book_id",bookEntity.getId()).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateTrans(Transactions transactionsEntity, Book bookEntity, User userEntity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        String status = "Available";
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            List<Transactions> transactions = new ArrayList<>();

            transactionsEntity.setUserList(userEntity);
            userEntity.setTransaction(transactions);

            transactionsEntity.setBookList(bookEntity);
            bookEntity.setTransactions(transactions);

            transactions.add(transactionsEntity);
            session.update(transactionsEntity);

            session.createQuery("UPDATE Book b SET b.status = :status WHERE b.id = : book_id")
                    .setParameter("status",status).setParameter("book_id",bookEntity.getId()).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public List<Transactions> getIncompleteTransactions(String userId) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        try {
//            Transaction transaction = session.beginTransaction();
//            Transactions transactions = session.get(Transactions.class,id);
//            transaction.commit();
//            return transactions;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            String hql = "FROM Transactions t WHERE t.status = :status AND t.user.id = :userId";
            Query<Transactions> query = session.createQuery(hql, Transactions.class);
            query.setParameter("status", "Incomplete");
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}