package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.AdminBO;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;
import lk.ijse.Controller.util.Validation;
import lk.ijse.DTO.AdminDTO;
import lk.ijse.DTO.UserDTO;

import java.io.IOException;
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

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    boolean password , user ;
    public static String GlobUserName;

    @Override
    public void initialize (URL url , ResourceBundle resourceBundle) {
        lblHide.setVisible(false);
    }

    @FXML
    void btnSingInOnAction(ActionEvent event) {
        String mail = txtMail.getText();
        String pw = txtPassword.getText();

        AdminDTO adminDTO = adminBO.getAdmin(mail);
        UserDTO userDTO = userBO.getUser(mail);

            if (adminDTO != null && adminDTO.getMail().equals(mail) && adminDTO.getPassword().equals(pw)) {
                // Admin login successful
                Navigation.navigation(Rout.DASHBOARD,root);
            } else if (userDTO != null && userDTO.getEmail().equals(mail)&& userDTO.getPassword().equals(pw)) {
                // User login successful
                Navigation.navigation(Rout.USER_DASHBOARD,root);
            } else {
                // Invalid credentials for both admin and user
        }
    }

    @FXML
    void btnSingUpOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose one");
        alert.setContentText("Choose an option:");

        ButtonType buttonTypeOne = new ButtonType("Admin");
        ButtonType buttonTypeTwo = new ButtonType("User");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeOne) {
                root.getChildren().clear();
                try {
                    Navigation.navigation(Rout.ADMIN_SIGNUP,root);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (response == buttonTypeTwo) {
                root.getChildren().clear();
                try {
                    Navigation.navigation(Rout.USER_SIGNUP,root);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
