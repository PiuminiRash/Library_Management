package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.Controller.util.Validation;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.TM.UserTM;
import lk.ijse.DTO.UserDTO;

public class CustomerFormController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Line lineEmail;

    @FXML
    private Line lineName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Line linePassword;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    boolean id,name,nic,email,password;
    @FXML
    void initialize() {
        setCellValueFactory();
        fillTable();
    }

    private void fillTable() {
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();
        for (UserDTO userDTO : userBO.getAll()) {
            userTMS.add(new UserTM(
                    userDTO.getEmail(),
                    userDTO.getName(),
                    userDTO.getPassword()
            ));
        }
        tblUser.setItems(userTMS);
    }

    private void setCellValueFactory() {
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (userBO.deleteUser(txtEmail.getText())) {
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","Deleted!","Delete Successful").show();
            fillTable();
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","Deleted!","Delete Unsuccessful!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validation()) {
            if (userBO.saveUser(new UserDTO(txtEmail.getText(),txtName.getText(),txtPassword.getText()))) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!","Save Successful!").show();
                fillTable();
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Save","Saved!","Save UnSuccess!!").show();
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String email = txtSearch.getText();
        UserDTO userDTO = userBO.getUser(email);
        if (userDTO!=null) {
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            txtEmail.setDisable(true);
            txtName.setDisable(false);
            txtPassword.setDisable(false);

            txtEmail.setText(userDTO.getEmail());
            txtName.setText(userDTO.getName());
            txtPassword.setText(userDTO.getPassword());
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Error","Invalid","Invalid Book Id!").show();
        }
        txtSearch.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (validation()) {
            if (userBO.updateUser(new UserDTO(txtEmail.getText(),txtName.getText(),txtPassword.getText()))) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Update","Updated!","Update Successful!").show();
                fillTable();
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Update","Not Updated!","Update UnSuccess!!").show();
            }
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    private boolean validation() {
//        id = false;
//        name = false;
//        nic = false;
//        email = false;
//        password = false;
//        id  = Validation.txtValidation(txtEmail,lineEmail);
//        name = Validation.txtValidation(txtName,lineName);
//        email = Validation.txtValidation(txtEmail,lineEmail);
//        password = Validation.passwordValidation(txtPassword,linePassword);
//        if (id && id && name && email && password) {
//            return true;
//        }
        return true;
    }

}
