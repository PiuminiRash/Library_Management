package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.BookBO;
import lk.ijse.DTO.BookDTO;
import lk.ijse.DTO.TM.BookTM;

public class UserBookController {
    @FXML
    private TableColumn<?,?> colAuthor;
    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<BookTM> tblBook;

    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);

    public void initialize(){
        setCellValueFactory();
        fillTable();

    }
    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void fillTable() {
        ObservableList<BookTM> bookTMS = FXCollections.observableArrayList();
        for (BookDTO bookDTO:bookBO.getAll()) {
            bookTMS.add(new BookTM(
                    bookDTO.getId(),
                    bookDTO.getTitle(),
                    bookDTO.getGenre(),
                    bookDTO.getAuthor(),
                    bookDTO.getStatus()
            ));
        }
        tblBook.setItems(bookTMS);
    }
}
