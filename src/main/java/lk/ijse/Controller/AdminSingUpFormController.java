package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.AdminBO;
import lk.ijse.BO.custom.BranchBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;
import lk.ijse.DTO.AdminDTO;
import lk.ijse.DTO.BranchDTO;

public class AdminSingUpFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lblBranchId;

    @FXML
    private TextField txtBranch;

    @FXML
    private Line lineEmail;

    @FXML
    private Line linePassword;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnSingUp;

    @FXML
    private JFXButton btnSingIn;

    @FXML
    private Label lblHide;

    @FXML
    private Line linePassword1;

    @FXML
    private PasswordField txtRePassword;

    @FXML
    private Line lineBranch;

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    private final BranchBO branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);

    boolean user , password , rePassword;

    @FXML
    public void initialize(){
        lblBranchId.setText(branchBO.getNextId());
    }

    @FXML
    void btnSingInOnAction(ActionEvent event) {
        Navigation.navigation(Rout.LOGIN,root);
    }

    @FXML
    void btnSingUpOnAction(ActionEvent event) {
        userValidation();
        if (user && password && rePassword) {
            boolean save = adminBO.saveAdmin(new AdminDTO(txtMail.getText(),txtPassword.getText()));
            if (save) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!","Save Successful!").show();
                boolean branchSave = branchBO.saveBranch(new BranchDTO(lblBranchId.getText(),txtBranch.getText()));
                if (branchSave) {
                    new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!","Branch Save Success!!").show();
                    clearFeild();
                }
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Save","Not Save!","Save Not Successful!").show();
            }
        }
    }

    public void clearFeild() {
        txtBranch.setText("");
        txtMail.setText("");
        txtPassword.setText("");
        txtRePassword.setText("");
    }

    private void userValidation() {
//        user = false;
//        password =  false;
//        rePassword =  false;
//        user = Validation.txtValidation(txtMail,lineEmail);
//        password = Validation.txtValidation(txtPassword,linePassword);
//        rePassword = Validation.txtValidation(txtRePassword,linePassword1);
//        if (!txtPassword.getText().equals(txtRePassword.getText())) {
//            Validation.shakeLine(linePassword);
//            Validation.shakeLine(linePassword1);
//        } else {
//           return;
//        }
    }
}
