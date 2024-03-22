package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.DTO.UserDTO;

import java.io.IOException;

public class UserSingUpFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private Line lineEmail;

    @FXML
    private Line lineUserName;

    @FXML
    private Line linePassword;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnSIngInOnAction(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/View/loginform.fxml")));

    }

    @FXML
    void btnSingUpOnAction(ActionEvent event) {
        String email = txtMail.getText();
        String name = txtUserName.getText();
        String pw = txtPassword.getText();

        try {
            var dto = new UserDTO(email, name,pw);
            boolean isReg = userBO.saveUser(dto);
            if (isReg) {
                new Alert(Alert.AlertType.CONFIRMATION, "User registered").show();
            } else {
                new Alert(Alert.AlertType.ERROR).show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}