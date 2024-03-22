package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Book;
import lk.ijse.Entity.Transactions;
import lk.ijse.Entity.User;
import org.hibernate.Transaction;

import java.util.List;

public interface TransactionDAO extends CrudDAO<Transactions,String> {
    String getNextId();
    boolean saveTrans(Transactions transactionsEntity, Book bookEntity , User userEntity);
    boolean updateTrans(Transactions transactionsEntity, Book bookEntity , User userEntity);
    List<Transactions> getIncompleteTransactions(String user);
}
