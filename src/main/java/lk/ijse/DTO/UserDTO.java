package lk.ijse.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class UserDTO {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String password;
}
