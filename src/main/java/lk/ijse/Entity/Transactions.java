package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transactions {
    @Id
    @Column(name = "trans_id",length = 50)
    private String id;
    @Column(name = "start_date",columnDefinition = "date")
    private String startDate;
    @Column(name = "end_date",columnDefinition = "date")
    private String endDate;
    @Column (name = "user")
    private String user;
    @Column(name = "book")
    private String book;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn
    private User userList;
    @ManyToOne
    @JoinColumn
    private Book bookList;

    public Transactions(String transId,String startDate,String endDate,String user,String book,String status) {
        this.id = transId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.book = book;
        this.status = status;
    }
}
