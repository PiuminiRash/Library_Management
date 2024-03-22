package lk.ijse.DTO.TM;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionTM {
    private String transId;
    private String startDate;
    private String endDate;
    private String bookId;
    private String userId;
    private String status;

    public TransactionTM(String transId,String startDate,String endDate,String bookId,String status){
        this.transId = transId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookId = bookId;
        this.status = status;
    }
}
