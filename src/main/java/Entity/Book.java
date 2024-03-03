package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @Column(name = "book_id",length = 5)
    private String id;
    @Column(name = "book_name")
    private String name;
    @Column(name = "book_type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "room")
    private List<Transaction> reservations = new ArrayList<>();
}
