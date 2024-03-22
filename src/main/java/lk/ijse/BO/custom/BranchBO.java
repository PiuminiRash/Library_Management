package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.BranchDTO;

import java.util.List;

public interface BranchBO extends SuperBO {
    List<BranchDTO> getAll();
    boolean saveBranch(BranchDTO branchDTO);
    boolean updateBranch(BranchDTO branchDTO);
    BranchDTO getBranch(String branchId);
    boolean deleteBranch(String branchId);
    String getNextId();
}
