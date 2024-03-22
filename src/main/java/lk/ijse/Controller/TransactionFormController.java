package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.BookBO;
import lk.ijse.BO.custom.TransactionBO;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.TM.BookTM;
import lk.ijse.DTO.TM.TransactionTM;
import lk.ijse.DTO.TransactionDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

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
    private TableView<TransactionTM> tblTransaction;

    @FXML
    private TableColumn<?, ?> colTransId;

    @FXML
    private TableColumn<?, ?> colStartDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colStatus;

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

    @FXML
    private TextField txtTransId;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TRANSACTION);
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    private final BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);

    @FXML
    void initialize(){
        setBookId();
        setUserId();
        setCellValueFactory();
        fillTable();
    }

    private void fillTable() {
        ObservableList<TransactionTM> transactionTMS = FXCollections.observableArrayList();
        for (TransactionDTO transactionDTO : transactionBO.getAllTransaction()) {
            transactionTMS.add(new TransactionTM(
                    transactionDTO.getTransId(),
                    transactionDTO.getStartDate(),
                    transactionDTO.getEndDate(),
                    transactionDTO.getUser(),
                    transactionDTO.getBook(),
                    transactionDTO.getStatus()
            ));
        }
        tblTransaction.setItems(transactionTMS);
    }

    private void setCellValueFactory() {
        colTransId.setCellValueFactory(new PropertyValueFactory<>("transId"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (transactionBO.deleteTransaction(txtTransId.getText())) {
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","Deleted!","Deleted Successful!!").show();
            fillTable();
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","Not Deleted!!","Delete Unsuccessful!!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        UserDTO userDTO = null;
        BookDTO bookDTO = null;

        String id = txtTransId.getText();
        String startDate = String.valueOf(lblDate.getText());
        String returnDate = String.valueOf(Datedate.getValue());
        String user = cmbUserId.getValue();
        String book = cmbBookId.getValue();

        for(UserDTO users : transactionBO.getAllUser()){
            userDTO = new UserDTO(users.getEmail(),users.getName(),users.getPassword());
        }

        for (BookDTO books : transactionBO.getAllBook()){
            bookDTO = new BookDTO(books.getId(),books.getTitle(),books.getAuthor(),books.getGenre());
        }

        var transactionDTO = new TransactionDTO(id,startDate,returnDate,user,book);
        boolean isSaved = transactionBO.saveTransaction(transactionDTO, bookDTO, userDTO);
            if (isSaved){
                new CustomAlert(Alert.AlertType.INFORMATION,"Transaction","Transaction","Transaction Save Success!!").show();
                fillTable();
            }else {
                new CustomAlert(Alert.AlertType.ERROR,"Transaction","Transaction","Transaction Save Unsuccessful!!").show();
            }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        UserDTO userDTO = null;
        BookDTO bookDTO = null;

        String id = txtTransId.getText();
        String startDate = String.valueOf(lblDate.getText());
        String returnDate = String.valueOf(Datedate.getValue());
        String user = cmbUserId.getValue();
        String book = cmbBookId.getValue();

        for(UserDTO users : transactionBO.getAllUser()){
            userDTO = new UserDTO(users.getEmail(),users.getName(),users.getPassword());
        }

        for (BookDTO books : transactionBO.getAllBook()){
            bookDTO = new BookDTO(books.getId(),books.getTitle(),books.getAuthor(),books.getGenre());
        }

        var transactionDTO = new TransactionDTO(id,startDate,returnDate,user,book);
        boolean isUpdate = transactionBO.updateTransaction(transactionDTO, bookDTO, userDTO);
        if (isUpdate){
            new CustomAlert(Alert.AlertType.INFORMATION,"Transaction","Transaction","Transaction Update Success!!").show();
            fillTable();
        }else {
            new CustomAlert(Alert.AlertType.ERROR,"Transaction","Transaction","Transaction Update Unsuccessful!!").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String transId = txtSearch.getText();
        TransactionDTO transactionDTO = transactionBO.getTransaction(transId);
        if (transactionDTO!=null) {
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            txtTransId.setDisable(true);
            txtBookName.setDisable(false);
            txtUserName.setDisable(false);
            cmbUserId.setDisable(false);
            cmbBookId.setDisable(false);
            lblDate.setDisable(false);
            Datedate.setDisable(false);

            txtTransId.setText(transactionDTO.getTransId());
            lblDate.setText(transactionDTO.getStartDate());
            Datedate.setValue(LocalDate.parse(transactionDTO.getEndDate()));
            cmbBookId.getSelectionModel().select(getCmbIndex(cmbBookId,transactionDTO.getBook()));
            cmbUserId.getSelectionModel().select(getCmbIndex(cmbUserId,transactionDTO.getUser()));
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Error","Invalid","Invalid Book Id!").show();
        }
        txtSearch.clear();
    }

    int getCmbIndex(ComboBox<String> cmb,String value) {
        List<String> cmbList = cmb.getItems();
        for (int i=0;i<cmbList.size();i++) {
            if (cmbList.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @FXML
    void cmbBookIdOnAction(ActionEvent actionEvent) {
        BookDTO bookDTO = transactionBO.getBook(cmbBookId.getValue());
        txtBookName.setText(bookDTO.getTitle());
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

