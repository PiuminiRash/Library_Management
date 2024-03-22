package lk.ijse.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;

public class UserDashboardFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Button btnHome;

    public void initialize(){
        Thread thread = new Thread(() -> Platform.runLater(() -> {
            Navigation.navigation(Rout.HOME,rootNode);
        }));
        thread.start();
    }

    @FXML
    void btnBookOnAction(ActionEvent event) {
        Navigation.navigation(Rout.USER_BOOK,rootNode);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        Navigation.navigation(Rout.HOME,rootNode);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Navigation.navigation(Rout.LOGIN,root);
    }

    @FXML
    void btnTransactionOnAction(ActionEvent event) {
        Navigation.navigation(Rout.USER_TRANSACTION,rootNode);
    }
}
