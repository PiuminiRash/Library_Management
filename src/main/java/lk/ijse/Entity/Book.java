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
    @Column(name = "book_title")
    private String title;
    @Column (name = "author")
    private String author;
    @Column(name = "genre")
    private String genre;
    @Column (name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookList")
    private List<Transactions> transactions;

    public Book(String id,String title,String author,String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String id,String title,String author,String genre,String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status=status;
    }
}
