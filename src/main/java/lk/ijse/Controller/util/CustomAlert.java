package lk.ijse.Controller.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomAlert extends Alert {
    public CustomAlert(AlertType alertType, String title, String header, String massage, ButtonType...buttonTypes) {
        super(alertType,massage,buttonTypes);
        setTitle(title);
        setHeaderText(header);

        String image = null;
        switch (alertType) {
            case ERROR:
                image = "Assets/close.png";
                break;
            case INFORMATION:
                image = "Assets/information-button.png";
                break;
            case WARNING:
                image = "Assets/warning.png";
                break;
        }

        if (image!=null) {
            ImageView imageView = new ImageView(new Image(image));
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            setGraphic(imageView);
        }
    }
}
