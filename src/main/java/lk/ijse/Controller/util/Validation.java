package lk.ijse.Controller.util;

import animatefx.animation.Shake;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Validation {
    static Shake shake;
    public static boolean txtValidation (TextField txt , Line line) {
        if (txt.getText().matches("^\\p{L}+\\s\\p{L}+$")) {
            shakeLine(line);
        } else {
            defaultLine(line);
            return true;
        }
        return false;
    }

    public static boolean passwordValidation (PasswordField passwordText, Line line) {
        if (passwordText.getText().matches("^\\d{4}$")) {
            shakeLine(line);
        } else {
            defaultLine(line);
            return false;
        }
        return false;
    }

    public static boolean cmdNumValidation (TextField txt,Line line) {
        if (txt.getText().matches("[0-9+]+")) {
            defaultLine(line);
            return true;
        } else {
            shakeLine(line);
        }
        return false;
    }

    public static boolean numberValidation(TextField txt,Line line) {
        if (txt.getText().matches("[0-9+]+")) {
            defaultLine(line);
            return true;
        } else {
            shakeLine(line);
        }
        return false;
    }
    public static boolean moneyValidation (TextField txt,Line line) {
        if (txt.getText().matches("\\d+(\\.\\d{1,2})?")) {
            defaultLine(line);
            return false;
        } else {
            shakeLine(line);
        }
        return false;
    }

    public static void shakeLine(Line line) {
        line.setStroke(Color.RED);
        shake = new Shake(line);
        shake.setOnFinished(actionEvent -> {
            defaultLine(line);
        });
        shake.play();
    }

    public static void defaultLine(Line line) {
        line.setStroke(Color.RED);
        new CustomAlert(Alert.AlertType.ERROR,"Validation","Invalid Enter Type","Data Enter Invalided!!!").show();
    }

    public static boolean dateValidation(DatePicker date) {
        if (date.getValue()==null) {
            shakeDate(date);
        } else {
            return false;
        }
        return false;
    }

    public static boolean cmbValidation (ComboBox<String> idCmb) {
        if (idCmb.getValue()==null) {
            shakeCmb(idCmb);
        } else {
            return true;
        }
        return false;
    }

    private static void shakeDate(DatePicker date) {
        date.setStyle(
                "-fx-background-color: red;" +
                        "-fx-border-width: 2px;" +
                        "-fx-background-color: traceparent;" +
                        "-fx-text-fill: white;"
        );
        shake = new Shake(date);
        shake.setOnFinished(actionEvent -> {
            defaultDate(date);
        });
        shake.play();
    }

    private static void defaultDate (DatePicker date) {
        date.setStyle(
                "-fx-background-color: tranceparent;" +
                        "-fx-text-fill: white"
        );
    }

    private static void shakeCmb (ComboBox<String> idCmb) {
        idCmb.setStyle(
                "-fx-border-color: red;" +
                        "-fx-border-width: 2px;" +
                        "-fx-background-color: tranceparent;"
        );
        shake = new Shake(idCmb);
        shake.setOnFinished(actionEvent -> {
            //defaultDate(idCmb);
        });
        shake.play();
    }

    private static void defaltDate (ComboBox<String> idCmb) {
        idCmb.setStyle(
                "-fx-background-color: tranceparent;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2px;"
        );
    }
}
