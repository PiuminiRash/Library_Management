package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.UserDTO;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);
    UserDTO getUser(UserDTO userDTO);
    boolean updateUser(UserDTO userDTO);
}
