package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.AdminDTO;

public interface AdminBO extends SuperBO {
    boolean saveUser(AdminDTO userDTO);
    AdminDTO getUser(AdminDTO userDTO);
    boolean updateUser(AdminDTO userDTO);
}
