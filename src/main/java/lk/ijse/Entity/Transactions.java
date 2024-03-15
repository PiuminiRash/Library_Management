package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


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
    private Date startDate;
    @Column(name = "end_date",columnDefinition = "date")
    private Date endDate;
    @Column(name = "res_status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id",
            referencedColumnName = "book_id")
    private Book book;

    public Transactions(String id,Date startDate,Date endDate,Book book,User user) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.book = book;
    }
}
