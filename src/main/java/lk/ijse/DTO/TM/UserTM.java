package lk.ijse.DTO.TM;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserTM {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String password;
}
