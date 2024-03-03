package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Transaction;

public interface TransactionDAO extends CrudDAO<Transaction,String> {
    String getNextId();
}
