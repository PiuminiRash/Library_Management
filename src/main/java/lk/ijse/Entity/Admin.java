package lk.ijse.Entity;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branches;

    public Admin(String mail,String password) {
        this.mail = mail;
        this.password = password;
    }
}
