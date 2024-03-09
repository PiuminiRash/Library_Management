package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;
import lk.ijse.Controller.util.Validation;
import lk.ijse.DTO.UserDTO;

import java.io.IOException;

public class SingUpFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private Line lineEmail;

    @FXML
    private Line linePassword;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblHide;

    @FXML
    private Line linePassword1;

    @FXML
    private JFXButton btnSingUp;

    @FXML
    private JFXButton btnSingIn;

    @FXML
    private PasswordField txtRePassword , Password;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    boolean user , password , rePassword;

    @FXML
    void btnSingInOnAction(ActionEvent event) {
        Navigation.navigation(Rout.LOGIN,root);
    }

    @FXML
    void btnSingUpOnAction(ActionEvent event) {
        userValidation();
        if (user && password && rePassword) {
            boolean save = userBO.saveUser(new UserDTO(txtMail.getText(),txtPassword.getText()));
            if (save) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!","Save Successful!").show();
                txtMail.setText("");
                txtPassword.setText("");
                txtRePassword.setText("");
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Save","Not Save!","Save Not Successful!").show();
            }
        }
    }

    @FXML
    void imgHide(MouseEvent event) {

    }

    @FXML
    void imgView(MouseEvent event) {

    }

    @FXML
    void txtMailOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtRePassword.requestFocus();
    }

    @FXML
    void txtRePasswordOnAction(ActionEvent event) {
        btnSingIn.fire();
    }

    private void userValidation() {
        user = false;
        password =  false;
        rePassword =  false;
        user = Validation.txtValidation(txtMail,lineEmail);
        password = Validation.txtValidation(txtPassword,linePassword);
        rePassword = Validation.txtValidation(txtRePassword,linePassword1);
        if (!txtPassword.getText().equals(txtRePassword.getText())) {
            Validation.shakeLine(linePassword);
            Validation.shakeLine(linePassword1);
        } else {
            Validation.defaultLine(linePassword);
            Validation.defaultLine(linePassword1);
        }
    }
}
