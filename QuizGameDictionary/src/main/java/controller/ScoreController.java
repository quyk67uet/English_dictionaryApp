package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ScoreController implements Initializable{
    @FXML
    private AnchorPane containerP;
    @FXML
    private Label Marks;
    @FXML
    private JFXButton result_button;
    @FXML
    private JFXButton back_button;

    private static ScoreController instance;

    public ScoreController()
    {
        instance = this;
    }

    public static ScoreController getInstance()
    {
        return instance;
    }

    public void backSFeatures() {
        try {
            ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.2), containerP);
            scaleOut.setToX(0);
            scaleOut.setToY(0);
            scaleOut.setOnFinished(event -> {
                try {
                    containerP.getChildren().clear();
                    Node startNode = new FXMLLoader(getClass().getResource("/FXMLViews/MenuView.fxml")).load();
                    containerP.getChildren().add(startNode);

                    ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.2), containerP);
                    scaleIn.setFromX(0);
                    scaleIn.setFromY(0);
                    scaleIn.setToX(1);
                    scaleIn.setToY(1);
                    scaleIn.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            scaleOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resultFeatures() {
        try {
            TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.2), containerP);
            slideOut.setToX(-containerP.getWidth());
            slideOut.setOnFinished(event -> {
                try {
                    containerP.getChildren().clear();
                    Node startNode = new FXMLLoader(getClass().getResource("/FXMLViews/QuizResult.fxml")).load();
                    containerP.getChildren().add(startNode);

                    QuizResultController.getInstance().renderQuestionsResult();

                    TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.2), containerP);
                    slideIn.setFromX(containerP.getWidth());
                    slideIn.setToX(0);
                    slideIn.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            slideOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        back_button.setOnAction(event -> {
            backSFeatures();
        });
        result_button.setOnAction(event -> {
            resultFeatures();
        });
        //Get Final Score
        int no = QuizController.getInstance().countCorrectAnswer();
        //Set Final Score
        Marks.setText(no+"/10");
    }


}
