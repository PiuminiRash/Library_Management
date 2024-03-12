package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAll();
    boolean saveCustomer(UserDTO customerDTO);
    boolean updateCustomer(UserDTO customerDTO);
    UserDTO getCustomer(String cusId);
    boolean deleteCustomer(String cusId);
    String getNextId();
}
