package lk.ijse.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.TransactionBO;
import lk.ijse.DTO.TM.TransactionTM;
import lk.ijse.DTO.TransactionDTO;

public class UserStockFormController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<TransactionTM> tblBook;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStart;

    @FXML
    private TableColumn<?, ?> colEnd;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEndDate;

    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TRANSACTION);

    public void initialize(){
        String userId = "piu@gmail.com";
        TransactionDTO incompleteReturn = transactionBO.getIncompleteReturn(userId);
        if (incompleteReturn != null) {
            txtBookId.setText(incompleteReturn.getBook()); // Assuming getBookId() returns the book ID
            txtEndDate.setText(incompleteReturn.getEndDate());
        } else {
            txtBookId.setText("No incomplete return for this user"); // Set some default value or handle as needed
        }
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
                    transactionDTO.getBook(),
                    transactionDTO.getStatus()
            ));
        }
        tblBook.setItems(transactionTMS);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("transId"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}
