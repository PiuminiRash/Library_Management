package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@ToString
@Table (name = "branch")
public class Branch {
    @Id
    @Column (name = "branch_id")
    private String branchId;
    @Column (name = "branch_name")
    private String branchName;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "branches")
    private List<Admin> adminList;

    public Branch(String branchId,String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }
}
