package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainController implements Initializable {

    @FXML
    private Tooltip tooltipP, tooltipS, tooltipR;
    @FXML
    private AnchorPane APane;

    @FXML
    private JFXButton start_button;

    private static MainController instance;


    private double xOffset;
    private double yOffset;

    private Media media;
    private MediaPlayer mediaPlayer;

    public MainController()
    {
        instance = this;
    }

    public static MainController getInstance()
    {
        return instance;
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

        media = new Media(getClass().getResource("/Sound/music.mp3").toExternalForm());

        mediaPlayer = new MediaPlayer(media);

        tooltipP.setShowDelay(Duration.seconds(0.5));
        tooltipS.setShowDelay(Duration.seconds(0.5));
        tooltipR.setShowDelay(Duration.seconds(0.5));
    }


    @FXML
    public void startAction(ActionEvent event) throws IOException {
        start_button.getScene().getWindow().hide();
        Stage quizStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLViews/TickBoxView.fxml"));
        Scene scene = new Scene(root);
        quizStage.initStyle(StageStyle.UNDECORATED);
        quizStage.setScene(scene);
        quizStage.show();
        quizStage.setResizable(false);

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
