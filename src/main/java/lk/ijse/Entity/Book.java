package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @Column(name = "book_id",length = 10)
    private String id;
    @Column(name = "book_name")
    private String name;
    @Column(name = "book_type")
    private String type;
    @Column (name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "book")
    private List<Transactions> reservations = new ArrayList<>();

    public Book(String id,String name,String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
