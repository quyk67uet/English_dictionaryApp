package org.controllers.dictionary;

import com.jfoenix.controls.JFXButton;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private JFXButton aboutButton, exitButton;

    public void aboutUsFeatures() {
        try {
            Stage stage = (Stage) aboutButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/AboutUsGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aboutButton.setOnAction(event -> {
            aboutUsFeatures();
        });

        exitButton.setOnAction(event -> {
            Task<Void> exitTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    System.exit(0);
                    return null;
                }
            };
            new Thread(exitTask).start();
        });
    }
}
