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
    @Column(name = "email",length = 50)
    private String email;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_pw")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "userList")
    private List<Transactions> transaction = new ArrayList<>();

    public User(String email,String name,String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
