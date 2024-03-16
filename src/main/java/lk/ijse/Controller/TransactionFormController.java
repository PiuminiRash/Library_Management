package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.BookBO;
import lk.ijse.BO.custom.TransactionBO;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.TransactionDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.User;

import java.sql.Date;

public class TransactionFormController {
    @FXML
    private AnchorPane rootNode;

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
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    private final BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
    boolean id,startDate,endDate,bookId,userId,status;

    @FXML
    void initialize(){
        setBookId();
        setUserId();
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
        UserDTO userDTO = null;
        BookDTO bookDTO = null;

        String id = txtTransId.getText();
        String startDate = lblDate.getText();
        String endDate = String.valueOf(Datedate.getValue());
        String user = cmbUserId.getValue();
        String book = cmbBookId.getValue();

        for (UserDTO users : userBO.getAll()) {
            userDTO = new UserDTO(users.getId(),users.getName(),users.getNic(),users.getEmail(),users.getPassword());
        }

        for (BookDTO books : bookBO.getAll()) {
            bookDTO = new BookDTO(books.getId(),books.getName(),books.getType());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    @FXML
    void cmbBookIdOnAction(ActionEvent actionEvent) {
        BookDTO bookDTO = transactionBO.getBook(cmbBookId.getValue());
        txtBookName.setText(bookDTO.getName());
    }

    @FXML
    void cmbUserIdOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = transactionBO.getUser(cmbUserId.getValue());
        txtUserName.setText(userDTO.getName());
    }

    private void setBookId() {
        ObservableList<String> bookIdList = FXCollections.observableArrayList();
        bookIdList.addAll(transactionBO.getBookId());
        cmbBookId.setItems(bookIdList);
    }

    private void setUserId() {
        ObservableList<String> userIdList = FXCollections.observableArrayList();
        userIdList.addAll(transactionBO.getUserId());
        cmbUserId.setItems(userIdList);
    }
}

