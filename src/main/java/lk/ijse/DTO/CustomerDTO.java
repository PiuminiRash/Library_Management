package lk.ijse.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class CustomerDTO {
    private String cusId;
    private String name;
    private String nic;
    private String address;
    private int contact;
}
