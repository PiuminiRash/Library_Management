package lk.ijse.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {
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
            try {
                Navigation.navigation(Rout.HOME,rootNode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        thread.start();
    }

    @FXML
    void btnBookOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.BOOK,rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.CUSTOMER,rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.LOGIN,root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnTransactionOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.TRANSACTION,rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadHome () {
        btnHome.fire();
    }
}
