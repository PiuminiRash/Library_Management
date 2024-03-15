package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.DTO.TransactionDTO;

import java.util.List;

public interface TransactionBO extends SuperBO {
    List<TransactionDTO> getAll();
    List<BookDTO> getAllBookId();
    List<String> getUserId();
    BookDTO getBook(String book);
    UserDTO getUser(String user);
    boolean saveTransaction(TransactionDTO transactionDTO);
    boolean updateTransaction(TransactionDTO transactionDTO);
    boolean deleteTransaction(String transactionId);
    String getNextId();
}
