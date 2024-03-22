package lk.ijse.BO.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.BO.custom.AdminBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.AdminDAO;
import lk.ijse.DTO.AdminDTO;
import lk.ijse.Entity.Admin;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO = (AdminDAO)DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public boolean saveAdmin(AdminDTO userDTO) {
        return adminDAO.save(new Admin(
                userDTO.getMail(),
                userDTO.getPassword()
        ));
    }

    @Override
    public AdminDTO getAdmin(String mail) {
        Admin admin = adminDAO.getItem(mail);
        if (admin!=null) {
            return new AdminDTO(admin.getMail(),admin.getPassword());
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        return null;
    }

    @Override
    public AdminDTO getAdmin(AdminDTO adminDTO) {
        return null;
    }

    @Override
    public boolean updateAdmin(AdminDTO userDTO) {
        return adminDAO.update(new Admin(
                userDTO.getMail(),
                userDTO.getPassword()
        ));
    }
}
