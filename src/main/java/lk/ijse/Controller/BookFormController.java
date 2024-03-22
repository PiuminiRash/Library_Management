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
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private ComboBox<String> cmbGerne;

    @FXML
    private TableView<BookTM> tblBook;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colGerne;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Line lineBookId;

    @FXML
    private Line lineBookName;

    @FXML
    private TextField txtAuthor;

    @FXML
    private Line lineAuthor;

    private final BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
    boolean name;

    public void initialize() {
        setCellValueFactory();
        fillTable();
        setType();
        txtBookId.setText(bookBO.getNextId());
    }

    public void loadBranch(String branch) {

    }

    private void fillTable() {
        ObservableList<BookTM> bookTM = FXCollections.observableArrayList();
        for (BookDTO bookDTO : bookBO.getAll()) {
            bookTM.add(new BookTM(
                    bookDTO.getId(),
                    bookDTO.getTitle(),
                    bookDTO.getAuthor(),
                    bookDTO.getGenre()
            ));
        }
        tblBook.setItems(bookTM);
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGerne.setCellValueFactory(new PropertyValueFactory<>("gerne"));
    }

    private void setType() {
        cmbGerne.getItems().setAll("Education", "PaperBook", "Romance", "Tool");
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
            if (bookBO.saveBook(new BookDTO(txtBookId.getText(),txtBookName.getText(),txtAuthor.getText(),cmbGerne.getValue()))) {
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
            if (bookBO.updateBook(new BookDTO(txtBookId.getText(),txtBookName.getText(),txtAuthor.getText(),cmbGerne.getValue()))) {
                new CustomAlert(Alert.AlertType.CONFIRMATION,"Update","Updated!","Update Successful!").show();
                fillTable();
            } else {
                new CustomAlert(Alert.AlertType.ERROR,"Update","Updated","Update Unsuccessful").show();
            }
        }
    }

    private boolean validation() {
//    name = false;
//    name = Validation.txtValidation(txtBookName,lineBookName);
//
//    if (name) {
//        return true;
//    }
    return true;
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
            txtAuthor.setDisable(false);
            cmbGerne.setDisable(false);

            txtBookId.setText(bookDTO.getId());
            txtBookName.setText(bookDTO.getTitle());
            txtAuthor.setText(bookDTO.getAuthor());
            cmbGerne.getSelectionModel().select(getCmbIndex(cmbGerne,bookDTO.getGenre()));
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
