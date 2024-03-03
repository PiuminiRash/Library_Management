package lk.ijse.DTO.TM;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTM {
    private String id;
    private String name;
    private String nic;
    private String address;
    private int contact;
}
