package lk.ijse.DAO.custom;

import lk.ijse.DAO.SuperDAO;
import lk.ijse.DTO.TransactionDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<TransactionDTO> getAllTransaction();
}
