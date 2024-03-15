package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.TransactionBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.BookDAO;
import lk.ijse.DAO.custom.QueryDAO;
import lk.ijse.DAO.custom.TransactionDAO;
import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.TransactionDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.Book;
import lk.ijse.Entity.Transactions;
import lk.ijse.Entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.BOOK);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.USER);
    TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public List<TransactionDTO> getAll() {
        List<TransactionDTO> transactionDTOS = queryDAO.getAllTransaction();
        return transactionDTOS;
    }

    @Override
    public List<String> getAllBookId() {
        List<String> bookIds = new ArrayList<>();
        for (Book book : bookDAO.getAll()) {
            bookIds.add(book.getId());
        }
        return bookIds;
    }

    @Override
    public List<String> getUserId() {
        List<String> userIds = new ArrayList<>();
        for (User user : userDAO.getAll()) {
            userIds.add(user.getId());
        }
        return userIds;
    }

    @Override
    public BookDTO getBook(String value) {
        Book book = bookDAO.getItem(value);
        return new BookDTO(
                book.getId(),
                book.getName(),
                book.getType()
        );
    }

    @Override
    public UserDTO getUser(String value) {
        User user = userDAO.getItem(value);
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getNic(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public boolean saveTransaction(TransactionDTO transactionDTO) {
        User user = userDAO.getItem(transactionDTO.getUserId());
        Book book = bookDAO.getItem(transactionDTO.getBookId());
        book.setStatus("Not Available");

        if (bookDAO.update(book)) {
            return transactionDAO.save(new Transactions(
                    transactionDTO.getTransId(),
                    (Date) transactionDTO.getStartDate(),
                    (Date) transactionDTO.getEndDate(),
                    book,
                    user
            ));
        }
        return false;
    }

    @Override
    public boolean updateTransaction(TransactionDTO transactionDTO) {
        Transactions transactions = transactionDAO.getItem(transactionDTO.getTransId());
        transactions.setStatus(transactionDTO.getStatus());
        transactions.setEndDate((Date) transactionDTO.getEndDate());
        return transactionDAO.update(transactions);
    }

    @Override
    public boolean deleteTransaction(String transactionId) {
        return transactionDAO.delete(transactionId);
    }

    @Override
    public String getNextId() {
        String id = transactionDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("TRS","")) + 1;
        return String.format("TRS--03d",newId);
    }
}
