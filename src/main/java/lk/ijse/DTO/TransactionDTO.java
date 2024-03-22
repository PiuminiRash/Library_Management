package lk.ijse.DTO;

import lk.ijse.Entity.Book;
import lk.ijse.Entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class TransactionDTO {
    private String transId;
    private String  startDate;
    private String endDate;
    private String user;
    private String book;
    private String status;

    public TransactionDTO(String transId,String startDate,String endDate,String status) {
        this.transId = transId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public TransactionDTO(String transId,String startDate,String endDate,String user,String book) {
        this.transId = transId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.book = book;
    }
}
