package org.features.dictionary;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertManagement {
    public Alert showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Alert");
        alert.setHeaderText(title);
        alert.setContentText(content);
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/assets/icons/alert.png")));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        alert.setGraphic(imageView);
        alert.getDialogPane().getStylesheets().add("/assets/alert.css");

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);

        return alert;
    }

    // public void showAlertConfirmation(String title, String content) {
    //     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //     Alert alertt = new Al
    //     alert.setTitle(title);
    //     alert.setHeaderText(null);
    //     alert.setContentText(content);
    //     alert.getDialogPane().getStylesheets().add("/assets/alert.css");
    //     alert
    // }

    // public void showAlertInfo(String title, String content) {
    //     Alert alert = new Alert(Alert.AlertType.INFORMATION);
    //     alert.setTitle(title);
    //     alert.setHeaderText(null);
    //     alert.setContentText(content);
    //     alert.showAndWait();
    // }

}
