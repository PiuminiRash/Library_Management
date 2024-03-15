package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.AdminBO;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;
import lk.ijse.Controller.util.Validation;
import lk.ijse.DTO.AdminDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Line lineEmail;

    @FXML
    private Line linePassword;

    @FXML
    private Label lblHide;

    private final AdminBO userBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);
    boolean password , user ;
    public static String GlobUserName;

    @Override
    public void initialize (URL url , ResourceBundle resourceBundle) {
        lblHide.setVisible(false);
    }
    @FXML
    void btnSingInOnAction(ActionEvent event) {
        validation();
        if (user && password) {
            AdminDTO isUser = userBO.getUser(new AdminDTO(txtMail.getText(),txtPassword.getText()));
            if (isUser!= null) {
                GlobUserName = txtMail.getText();

                if (txtPassword.getText().equals(isUser.getPassword())) {
                    Navigation.navigation(Rout.DASHBOARD,root);
                } else {
                    Validation.shakeLine(linePassword);
                }
            } else {
                Validation.shakeLine(lineEmail);
                Validation.shakeLine(linePassword);
            }
        }
    }

    @FXML
    void btnSingUpOnAction(ActionEvent event) {
        Navigation.navigation(Rout.SIGNUP,root);
    }

    @FXML
    void imgHide(MouseEvent event) {

    }

    @FXML
    void imgView(MouseEvent event) {

    }

    private void validation() {
        password = false;
        user = false;
        user = Validation.txtValidation(txtMail,lineEmail);
        password = Validation.passwordValidation(txtPassword,linePassword);
    }
}
