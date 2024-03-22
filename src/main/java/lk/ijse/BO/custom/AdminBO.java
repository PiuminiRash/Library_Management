package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.AdminDTO;

public interface AdminBO extends SuperBO {
    boolean saveAdmin(AdminDTO userDTO);
    AdminDTO getAdmin(String mail);
    AdminDTO getAdmin(AdminDTO adminDTO);
    boolean updateAdmin(AdminDTO userDTO);
}
