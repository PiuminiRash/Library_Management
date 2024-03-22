package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.BranchBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.BranchDAO;
import lk.ijse.DTO.BranchDTO;
import lk.ijse.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {
    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.BRANCH);

    @Override
    public List<BranchDTO> getAll() {
        List<BranchDTO> branchDTOS = new ArrayList<>();
        List<Branch> branches = branchDAO.getAll();
        if (branches != null) {
            for (Branch branch : branches) {
                branchDTOS.add(new BranchDTO(
                        branch.getBranchId(),
                        branch.getBranchName()
                ));
            }
        }
        return branchDTOS;
    }

    @Override
    public boolean saveBranch(BranchDTO branchDTO) {
        return branchDAO.save(new Branch(
                branchDTO.getBranchId(),
                branchDTO.getBranchName()
        ));
    }

    @Override
    public boolean updateBranch(BranchDTO branchDTO) {
        return branchDAO.update(new Branch(
                branchDTO.getBranchId(),
                branchDTO.getBranchName()
        ));
    }

    @Override
    public BranchDTO getBranch(String branchId) {
        return null;
    }

    @Override
    public boolean deleteBranch(String branchId) {
        return branchDAO.delete(branchId);
    }

    @Override
    public String getNextId() {
        String id = branchDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("BR","")) + 1;
        return String.format("BR%02d",newId);
    }
}
