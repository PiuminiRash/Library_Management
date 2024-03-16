package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Book;
import lk.ijse.Entity.Transactions;
import lk.ijse.Entity.User;

public interface TransactionDAO extends CrudDAO<Transactions,String> {
    String getNextId();
    boolean saveTrans(Transactions transactionsEntity, Book bookEntity , User userEntity);
}
