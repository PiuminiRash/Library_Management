package lk.ijse.DTO.TM;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookTM {
    private String id;
    private String title;
    private String author;
    private String gerne;
    private String status;

    public BookTM(String id,String title,String author,String gerne) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.gerne = gerne;
    }
}
