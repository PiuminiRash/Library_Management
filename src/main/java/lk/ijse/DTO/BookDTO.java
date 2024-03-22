package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class BookDTO {
    private String id;
    private String title;
    private String author;
    private String  genre;
    private String status;

    public BookDTO(String id,String title,String author,String genre) {
        this.id=id;
        this.title= title;
        this.author=author;
        this.genre=genre;
    }
}
