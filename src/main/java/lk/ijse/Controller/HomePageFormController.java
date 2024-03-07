package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import lk.ijse.Controller.util.Navigation;
import lk.ijse.Controller.util.Rout;

import java.io.IOException;

public class HomePageFormController {
    @FXML
    private AnchorPane settingPane;

    private static int round=0;
    @FXML
    void btnSettingOnAction(ActionEvent event) {
        try {
            switch (round) {
                case 0:
                    Navigation.navigation(Rout.SETTING,settingPane);
                    round = 1;
                    break;
                case 1 :
                    settingPane.getChildren().clear();
                    round = 0;
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
