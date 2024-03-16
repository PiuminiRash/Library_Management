package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.AdminBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.AdminDAO;
import lk.ijse.DTO.AdminDTO;
import lk.ijse.Entity.Admin;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO = (AdminDAO)DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public boolean saveUser(AdminDTO userDTO) {
        return adminDAO.save(new Admin(
                userDTO.getMail(),
                userDTO.getPassword()
        ));
    }

    @Override
    public AdminDTO getUser(AdminDTO userDTO) {
        Admin user = adminDAO.getItem(userDTO.getMail());
        if (user!=null) {
           return new AdminDTO(user.getMail(),user.getPassword());
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUser(AdminDTO userDTO) {
        return adminDAO.update(new Admin(
                userDTO.getMail(),
                userDTO.getPassword()
        ));
    }
}
