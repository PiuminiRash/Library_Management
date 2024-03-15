package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id",length = 50)
    private String id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_NIC")
    private String nic;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_pw")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "user")
    private List<Transactions> transaction = new ArrayList<>();

    public User(String id,String name,String nic,String email,String password) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.password = password;
    }
}
