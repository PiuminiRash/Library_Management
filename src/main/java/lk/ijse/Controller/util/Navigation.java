package lk.ijse.Controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static AnchorPane root;

    public static void navigation(Rout rout, AnchorPane root) {
        Navigation.root = root;
        Navigation.root.getChildren().clear();
        Stage window = (Stage) Navigation.root.getScene().getWindow();
        try {
            switch (rout) {
                case LOGIN:
                    initUi("loginform.fxml");
                    break;
                case ADMIN_SIGNUP:
                    initUi("singupform.fxml");
                    break;
                case DASHBOARD:
                    initUi("dashboardform.fxml");
                    break;
                case HOME:
                    initUi("homepageform.fxml");
                    break;
                case SETTING:
                    initUi("settingpageform.fxml");
                    break;
                case BOOK:
                    initUi("bookform.fxml");
                    break;
                case CUSTOMER:
                    initUi("customerform.fxml");
                    break;
                case TRANSACTION:
                    initUi("transactionform.fxml");
                    break;
                case USER_SIGNUP:
                    initUi("user_signup.fxml");
                    break;
                case USER_DASHBOARD:
                    initUi("userdashboardform.fxml");
                    break;
                case USER_BOOK:
                    initUi("user_bookform.fxml");
                    break;
                case USER_TRANSACTION:
                    initUi("user_stock.fxml");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error according to your application's requirements
        }
    }

    private static void initUi(String location) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/" + location)));
        Navigation.root.getChildren().add(pane);
    }
}

