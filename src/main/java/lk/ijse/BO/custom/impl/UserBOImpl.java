package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.UserBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.User;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(
                userDTO.getMail(),
                userDTO.getPassword()
        ));
    }

    @Override
    public UserDTO getUser(UserDTO userDTO) {
        User user = userDAO.getItem(userDTO.getMail());
        if (user!=null) {
           return new UserDTO(user.getMail(),user.getPassword());
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(
                userDTO.getMail(),
                userDTO.getPassword()
        ));
    }
}
