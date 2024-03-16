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
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Line lineId;

    @FXML
    private Line lineName;

    @FXML
    private TextField txtNIC;

    @FXML
    private Line lineNIC;

    @FXML
    private TextField txtEmail;

    @FXML
    private Line lineEmail;

    @FXML
    private PasswordField txtPassword;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    boolean id,name,nic,email,password;
    @FXML
    void initialize() {
        setCellValueFactory();
        fillTable();
        txtId.setText(userBO.getNextId());
    }

    private void fillTable() {
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();
        for (UserDTO userDTO : userBO.getAll()) {
            userTMS.add(new UserTM(
                    userDTO.getId(),
                    userDTO.getName(),
                    userDTO.getNic(),
                    userDTO.getEmail(),
                    userDTO.getPassword()
            ));
        }
        tblUser.setItems(userTMS);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (userBO.deleteUser(txtId.getText())) {
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","Deleted!","Delete Successful").show();
            fillTable();
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","Deleted!","Delete Unsuccessful!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
//        if (validation()) {
            if (userBO.saveUser(new UserDTO(txtId.getText(),txtName.getText(),txtNIC.getText(),txtEmail.getText(),txtPassword.getText()))) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!","Save Successful!").show();
                fillTable();
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Save","Saved!","Save UnSuccess!!").show();
            }
       // }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (validation()) {
            if (userBO.updateUser(new UserDTO(txtId.getText(),txtName.getText(),txtNIC.getText(),txtEmail.getText(),txtPassword.getText()))) {
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
        id = false;
        name = false;
        nic = false;
        email = false;
        password = false;
        id  = Validation.txtValidation(txtId,lineId);
        name = Validation.txtValidation(txtName,lineName);
        nic = Validation.txtValidation(txtNIC,lineNIC);
        email = Validation.txtValidation(txtEmail,lineEmail);
        password = Validation.passwordValidation(txtPassword,lineNIC);
        if (id && id && name && email && password) {
            return true;
        }
        return false;
    }

}
