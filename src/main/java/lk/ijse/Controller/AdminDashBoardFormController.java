package lk.ijse.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashBoardFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private Button btnHome;

    @FXML
    private AnchorPane rootNode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadHome();
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        Thread thread = new Thread(() -> Platform.runLater(() -> {
            Navigation.navigation(Rout.HOME,rootNode);
        }));
        thread.start();
    }

    @FXML
    void btnBookOnAction(ActionEvent event) {
        Navigation.navigation(Rout.BOOK,rootNode);
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        Navigation.navigation(Rout.CUSTOMER,rootNode);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Navigation.navigation(Rout.LOGIN,root);
    }

    @FXML
    void btnTransactionOnAction(ActionEvent event) {
        Navigation.navigation(Rout.TRANSACTION,rootNode);
    }

    private void loadHome () {
        btnHome.fire();
    }
}
