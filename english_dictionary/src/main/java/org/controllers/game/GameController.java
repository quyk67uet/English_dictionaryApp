package org.controllers.game;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controllers.MainController;
import org.features.game.*;

public class GameController implements Initializable {
    @FXML
    private Tooltip tooltipP, tooltipS, tooltipR;
    @FXML
    private BorderPane APane;

    @FXML
    private JFXButton startButton;

    private static GameController instance;

    public GameController()
    {
        instance = this;
    }

    public static GameController getInstance()
    {
        return instance;
    }

    public void startFeatures() {
        try {
            QuestionController.qQid = 0;
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), APane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) startButton.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/TickBoxView.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), APane);
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        startButton.setOnAction(event -> {
            startFeatures();
        });

        tooltipP.setShowDelay(Duration.seconds(0.5));
        tooltipS.setShowDelay(Duration.seconds(0.5));
        tooltipR.setShowDelay(Duration.seconds(0.5));

    }

    public BorderPane getAPane() {
        return APane;
    }

    @FXML
    void closeApp(ActionEvent event) {

        System.exit(0); //exit from application

    }

    public void playMusic() {
        MainController.getMediaPlayer("/assets/sounds/music.mp3").play();
    }

    public void pauseMusic() {

        MainController.getMediaPlayer("/assets/sounds/music.mp3").pause();
    }

    public void resetMusic() {

        MainController.getMediaPlayer("/assets/sounds/music.mp3").seek(Duration.seconds(0));
    }
}