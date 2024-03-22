package lk.ijse.BO.custom.impl;

import javafx.scene.control.Alert;
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
    public List<String> getBookId() {
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
            userIds.add(user.getEmail());
        }
        return userIds;
    }

    @Override
    public BookDTO getBook(String value) {
        Book book = bookDAO.getItem(value);
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre()
        );
    }

    @Override
    public boolean saveTransaction(TransactionDTO transactionDTO,BookDTO bookDTO,UserDTO userDTO) {
        String trans_status = "Incomplete";
        return transactionDAO.saveTrans(
                new Transactions(transactionDTO.getTransId(),transactionDTO.getStartDate(),transactionDTO.getEndDate(),transactionDTO.getUser(),transactionDTO.getBook(),trans_status),
                new Book(bookDTO.getId(),bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre()),
                new User(userDTO.getEmail(),userDTO.getName(),userDTO.getPassword()));
    }

    @Override
    public boolean updateTransaction(TransactionDTO transactionDTO,BookDTO bookDTO,UserDTO userDTO) {
        String status = "Complete";
        return transactionDAO.updateTrans(
                new Transactions(transactionDTO.getTransId(),transactionDTO.getStartDate(),transactionDTO.getEndDate(),transactionDTO.getUser(),transactionDTO.getBook(),status),
                new Book(bookDTO.getId(),bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre()),
                new User(userDTO.getEmail(),userDTO.getName(),userDTO.getPassword()));
    }

    @Override
    public boolean deleteTransaction(String transactionId) {
        return transactionDAO.delete(transactionId);
    }

    @Override
    public List<TransactionDTO> getAllTransaction() {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        List<Transactions> transactionsList = transactionDAO.getAll();

        if (transactionDTOS!=null) {
            for (Transactions transactions : transactionsList) {
                transactionDTOS.add(new TransactionDTO(
                        transactions.getId(),
                        transactions.getStartDate(),
                        transactions.getEndDate(),
                        transactions.getUser(),
                        transactions.getBook(),
                        transactions.getStatus()
                ));
            }
        }
        return transactionDTOS;
    }

    @Override
    public TransactionDTO getTransaction(String id) {
        Transactions transactions = transactionDAO.getItem(id);
        if (transactions!=null) {
            return new TransactionDTO(transactions.getId(), transactions.getStartDate(),transactions.getEndDate(), transactions.getUser(), transactions.getBook());
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        return null;
    }

    @Override
    public String getNextId() {
        String id = transactionDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("TRS","")) + 1;
        return String.format("TRS%03d",newId);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userList){
            userDTOS.add(new UserDTO(user.getEmail(),user.getName(),user.getPassword()));
        }
        return userDTOS;
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> booksList = bookDAO.getAll();
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : booksList){
            bookDTOS.add(new BookDTO(book.getId(),book.getTitle(),book.getAuthor(), book.getGenre()));
        }
        return bookDTOS;
    }

    @Override
    public UserDTO getUser(String value) {
        User user = userDAO.getItem(value);
        return new UserDTO(
                user.getEmail(),
                user.getName(),
                user.getPassword()
        );
    }

    @Override
    public TransactionDTO getIncompleteReturn(String user) {
        Transactions transactions = (Transactions) transactionDAO.getIncompleteTransactions(user);
        if (transactions != null) {
            return new TransactionDTO(
                    transactions.getId(),
                    transactions.getStartDate(),
                    transactions.getEndDate(),
                    transactions.getUser(),
                    transactions.getBook(),
                    transactions.getStatus()
            );
        } else {
            return null; // or handle as needed if no incomplete transaction found for the user
        }
    }
}
