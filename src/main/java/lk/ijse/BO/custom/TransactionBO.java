package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.DTO.TransactionDTO;
import lk.ijse.Entity.Book;
import lk.ijse.Entity.User;

import java.util.List;

public interface TransactionBO extends SuperBO {
    List<TransactionDTO> getAll();
    List<String> getBookId();
    List<String> getUserId();
    BookDTO getBook(String book);
    UserDTO getUser(String user);
    boolean saveTransaction(TransactionDTO transactionDTO, BookDTO bookDTO, UserDTO userDTO);
    boolean updateTransaction(TransactionDTO transactionDTO,BookDTO bookDTO,UserDTO userDTO);
    boolean deleteTransaction(String transactionId);
    List<TransactionDTO> getAllTransaction();
    TransactionDTO getTransaction(String id);
    String getNextId();
    List<UserDTO> getAllUser();
    List<BookDTO> getAllBook();
    TransactionDTO getIncompleteReturn(String user);
}
