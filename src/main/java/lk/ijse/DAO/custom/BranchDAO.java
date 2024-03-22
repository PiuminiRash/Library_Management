package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Branch;

public interface BranchDAO extends CrudDAO<Branch,String> {
    String getNextId();
}
