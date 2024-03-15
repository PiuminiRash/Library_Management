package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.TransactionBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.TransactionDTO;
import lk.ijse.DTO.UserDTO;

import java.sql.Date;

public class TransactionFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<String> cmbUserId;

    @FXML
    private TableView<?> tblBook;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TextField txtTransId;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtUserName;

    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private DatePicker Datedate;

    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TRANSACTION);
    boolean id,startDate,endDate,bookId,userId,status;

    @FXML
    void initialize(){
        cmbBookIdOnAction();
        cmbUserIdOnAction();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (transactionBO.deleteTransaction(txtTransId.getText())) {
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","Deleted!","Deleted Successful!!").show();
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","Not Deleted!!","Delete Unsuccessful!!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (transactionBO.saveTransaction(new TransactionDTO(txtTransId.getText(), Date.valueOf(lblDate.getText()),Date.valueOf(Datedate.getValue()),cmbUserId.getValue(),cmbBookId.getValue()))) {
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!!","Save Successful!!").show();
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Save","Not Saved!!","Saved Unsuccessful").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (transactionBO.updateTransaction(new TransactionDTO(txtTransId.getText(),Date.valueOf(lblDate.getText()),Date.valueOf(Datedate.getValue()),cmbUserId.getValue(),cmbBookId.getValue()))) {
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Update","Updated!!","Update Successful!!").show();
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Update","Not Updated!!","Updated Unsuccessful").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    @FXML
    void cmbBookIdOnAction() {
        BookDTO bookDTO = transactionBO.getBook(cmbBookId.getValue());
        txtBookName.setText(bookDTO.getName());
    }

    @FXML
    void cmbUserIdOnAction() {
        UserDTO userDTO = transactionBO.getUser(cmbUserId.getValue());
        txtUserName.setText(userDTO.getName());
    }

}

