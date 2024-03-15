package lk.ijse.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_mail")
    private String mail;
    @Column(name = "admin_pw")
    private String password;
}
