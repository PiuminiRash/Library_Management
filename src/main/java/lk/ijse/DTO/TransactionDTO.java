package lk.ijse.DTO;

import lombok.*;

import java.time.LocalDate;
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
    private String userId;
    private String bookId;
    private String status;
//    private String userName;
//    private String bookName;

    public TransactionDTO(String transId, Date startDate, Date endDate, String userId, String bookId) {
        this.transId = transId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.bookId = bookId;
    }
}
