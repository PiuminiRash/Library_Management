package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAll();
    boolean saveUser(UserDTO customerDTO);
    boolean updateUser(UserDTO customerDTO);
    UserDTO getUser(String mail);
    boolean deleteUser(String cusId);
    String getNextId();
}
