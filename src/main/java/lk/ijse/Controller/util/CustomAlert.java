package lk.ijse.Controller.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class CustomAlert extends Alert {
    public CustomAlert(AlertType alertType, String title, String header, String massage, ButtonType...buttonTypes) {
        super(alertType,massage,buttonTypes);
        setTitle(title);
        setHeaderText(header);

        String image = null;
        switch (alertType) {
            case ERROR:
                image = "src/main/resources/Assets/img.png";
                break;
            case INFORMATION:
                image = "src/main/resources/Assets/information-button.png";
                break;
            case WARNING:
                image = "src/main/resources/Assets/warning.png";
                break;
        }

        if (image!=null) {
            ImageView imageView = new ImageView((Element) new Image(image));
            imageView.setSize(40,40);
//            setGraphic(imageView);
        }
    }
}
