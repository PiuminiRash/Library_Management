package lk.ijse.Controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static AnchorPane root;
    public static void navigation(Rout rout,AnchorPane root) throws IOException {
        Navigation.root = root;
        Navigation.root.getChildren().clear();
        Stage window = (Stage) Navigation.root.getScene().getWindow();
        switch (rout) {
            case LOGIN:
                initUi("logInForm.fxml");
                break;
            case SING_UP:
                initUi("singUpForm.fxml");
                break;
            case DASH_BOARD:
                initUi("dashboardForm.fxml");
                break;
            case HOME:
                initUi("homeForm.fxml");
                break;
            case SETTING:
                initUi("settingForm.fxml");
                break;
            case BOOK:
                initUi("BookForm.fxml");
                break;
            case CUSTOMER:
                initUi("customerForm.fxml");
                break;
            case TRANSACTION:
                initUi("transactionForm.fxml");
                break;
        }
    }

    private static void initUi(String location) throws IOException {
        Navigation.root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/View/"+location))));
    }
}
