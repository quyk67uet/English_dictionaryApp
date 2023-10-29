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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private AnchorPane APane;

    @FXML
    private JFXButton start_button;

    private static MainController instance;


    private double xOffset;
    private double yOffset;

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

    }


    @FXML
    public void startAction(ActionEvent event) throws IOException {
        start_button.getScene().getWindow().hide();
        Stage quizStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLViews/TickBoxView.fxml"));
        Scene scene = new Scene(root);
        quizStage.setScene(scene);
        quizStage.show();
        quizStage.setResizable(false);


    }

    @FXML
    void closeApp(ActionEvent event) {

        System.exit(0); //exit from application

    }
}
