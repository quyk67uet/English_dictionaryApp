package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import application.Main;
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
import model.CountDown;

public class GameController implements Initializable {
    @FXML
    private Tooltip tooltipP, tooltipS, tooltipR;
    @FXML
    private BorderPane APane;

    @FXML
    private JFXButton startButton;

    private static GameController instance;

    private double xOffset;
    private double yOffset;

    private Media media;
    private MediaPlayer mediaPlayer;

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
            GrammarController.qQid = 0;
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), APane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) startButton.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXMLViews/TickBoxView.fxml")));
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
        //Draggeable Screen
        APane.setOnMousePressed(event -> {
            xOffset = Main.getPrimaryStage().getX() - event.getScreenX();
            yOffset = Main.getPrimaryStage().getY() - event.getScreenY();
            APane.setCursor(Cursor.CLOSED_HAND);
        });

        APane.setOnMouseDragged(event -> {
            Main.getPrimaryStage().setX(event.getScreenX() + xOffset);
            Main.getPrimaryStage().setY(event.getScreenY() + yOffset);

        });

        APane.setOnMouseReleased(event -> {
            APane.setCursor(Cursor.DEFAULT);
        });

        startButton.setOnAction(event -> {
            startFeatures();
        });

        media = new Media(getClass().getResource("/Sound/music.mp3").toExternalForm());

        mediaPlayer = new MediaPlayer(media);

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
        mediaPlayer.play();
    }

    public void pauseMusic() {

        mediaPlayer.pause();
    }

    public void resetMusic() {

        mediaPlayer.seek(Duration.seconds(0));
    }
}
