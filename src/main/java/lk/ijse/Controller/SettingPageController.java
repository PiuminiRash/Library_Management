package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.AdminBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.Controller.util.Validation;
import lk.ijse.DTO.AdminDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingPageController implements Initializable {
    @FXML
    private AnchorPane SettingPane;

    @FXML
    private PasswordField txtCurrentPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private PasswordField txtRePassword;

    @FXML
    private Line lineCurrentPassword;

    @FXML
    private Line lineNewPassword;

    @FXML
    private Line lineReEnter;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSave;

    private final AdminBO userBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initUi();
        lineCurrentPassword.requestFocus();
    }

    private void initUi() {
        txtCurrentPassword.clear();
        txtNewPassword.clear();
        txtRePassword.clear();
        txtCurrentPassword.setDisable(true);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (Validation.passwordValidation(txtRePassword,lineNewPassword)) {
           if (txtNewPassword.getText().equals(txtRePassword.getText())) {
               new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!","Save succefully").show();
               initUi();
           } else {
               new CustomAlert(Alert.AlertType.ERROR,"Not Save" , "Not Saved!" ,"Save Unsucceefully").show();
           }
        } else {
            Validation.shakeLine(lineNewPassword);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        AdminDTO userDTO = userBO.getUser(new AdminDTO(LoginFormController.GlobUserName,txtCurrentPassword.getText()));
        if (!txtCurrentPassword.getText().equals("")) {
            if (userDTO.getPassword().equals((txtCurrentPassword.getText()))) {
                txtRePassword.setDisable(false);
                btnSave.setDisable(false);
                txtNewPassword.setDisable(false);
                txtNewPassword.requestFocus();
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Invalid","Invalid Password!","Password is wrong.Try again!").show();
            }
        } else {
            Validation.shakeLine(lineCurrentPassword);
        }
    }

    @FXML
    void txtCurrentPasswordOnAction(ActionEvent event) {
        btnSearch.fire();
    }

    @FXML
    void txtRePasswordOnAction(ActionEvent event) {
        btnSave.fire();
    }
}
