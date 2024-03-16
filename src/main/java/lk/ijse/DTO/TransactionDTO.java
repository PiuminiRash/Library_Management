package lk.ijse.DTO;

import lk.ijse.Entity.Book;
import lk.ijse.Entity.User;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
public class TransactionDTO {
    private String transId;
    private Date startDate;
    private Date endDate;
    private User userId;
    private Book bookId;
    private String status;
//    private String userName;
//    private String bookName;

    public TransactionDTO(String transId, Date startDate, Date endDate, User userId, Book bookId) {
        this.transId = transId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.bookId = bookId;
    }
}
