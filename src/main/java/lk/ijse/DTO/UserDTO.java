package lk.ijse.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDTO {
    private String email;
    private String name;
    private String password;

    public UserDTO(String email,String password) {
        this.email=email;
        this.password=password;
    }
}
