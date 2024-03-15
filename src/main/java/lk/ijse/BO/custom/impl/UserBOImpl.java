package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.UserBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public List<UserDTO> getAll() {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userList) {
            userDTOS.add(new UserDTO(user.getId(),user.getName(),user.getNic(),user.getEmail(),user.getPassword()));
        }
        return userDTOS;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getNic(),
                userDTO.getEmail(),
                userDTO.getPassword()
                ));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getNic(),
                userDTO.getEmail(),
                userDTO.getPassword()
        ));
    }

    @Override
    public UserDTO getUser(String cusId) {
        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        return userDAO.delete(id);
    }

    @Override
    public String getNextId() {
        String id = userDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("C","")) + 1;
        return String.format("C%03d",newId);
    }
}
