package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class BranchDTO {
    private String branchId;
    private String branchName;

    public  BranchDTO(String branchId) {
        this.branchId = branchId;
    }
}
