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
import lk.ijse.BO.custom.BookBO;
import lk.ijse.Controller.util.CustomAlert;
import lk.ijse.Controller.util.Validation;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.TM.BookTM;

import java.util.List;

public class BookFormController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TableView<BookTM> tblBook;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBookType;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Line lineBookId;

    @FXML
    private Line lineBookName;


    private final BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
    boolean id,name,type;

    public void initialize() {
        setCellValueFactory();
        fillTable();
        setType();
        txtBookId.setText(bookBO.getNextId());
    }

    private void fillTable() {
        ObservableList<BookTM> bookTM = FXCollections.observableArrayList();
        for (BookDTO bookDTO : bookBO.getAll()) {
            bookTM.add(new BookTM(
                    bookDTO.getId(),
                    bookDTO.getName(),
                    bookDTO.getType()
            ));
        }
        tblBook.setItems(bookTM);
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBookType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    private void setType() {
        cmbType.getItems().setAll("Education","Novel","Tool");
    }

    private void setBookId() {
        txtBookId.setText(bookBO.getNextId());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (bookBO.deleteBook(txtBookId.getText())) {
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete","Deleted!","Deleted Successful!").show();
            fillTable();
        } else {
            new CustomAlert(Alert.AlertType.ERROR,"Delete","Deleted!","Delete UnSuccessful").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validation()) {
            if (bookBO.saveBook(new BookDTO(txtBookId.getText(),txtBookName.getText(),cmbType.getValue()))) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Save","Saved!","Book Save Successful!").show();
                fillTable();
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Save","Saved!","Save Unsuccessful!").show();
            }
        } else if (!validation()){
            new CustomAlert(Alert.AlertType.ERROR,"Validation","Invalid Enter Type","Invalid data Entered!!!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (validation()) {
            if (bookBO.updateBook(new BookDTO(txtBookId.getText(),txtBookName.getText(),cmbType.getValue()))) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Update","Updated!","Update Successful!").show();
                fillTable();
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Update","Updated","Update Unsuccessful").show();
            }
        }
    }

    private boolean validation() {
    id = false;
    name = false;
    type = false;
    id = Validation.txtValidation(txtBookId,lineBookId);
    name = Validation.txtValidation(txtBookName,lineBookName);
    type = Validation.cmbValidation(cmbType);

    if (id  && name && type) {
        return true;
    }
    return false;
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String bookId = txtSearch.getText();
        BookDTO bookDTO = bookBO.getBook(bookId);
        if (bookDTO!=null) {
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            txtBookId.setDisable(true);
            txtBookName.setDisable(false);
            cmbType.setDisable(false);

            txtBookId.setText(bookDTO.getId());
            txtBookName.setText(bookDTO.getName());
            cmbType.getSelectionModel().select(getCmbIndex(cmbType,bookDTO.getType()));
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
}
