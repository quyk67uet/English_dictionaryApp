package org.controllers.game;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScoreController implements Initializable{
    @FXML
    private BorderPane containerP;
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
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), containerP);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) back_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/GameGraphical.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), containerP);
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

    public void resultFeatures() {
        try {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), containerP);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) result_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/QuizResult.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

                    QuizResultController.getInstance().renderQuestionsResult();

                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), containerP);
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
        back_button.setOnAction(event -> {
            backSFeatures();
        });
        result_button.setOnAction(event -> {
            resultFeatures();
        });
        //Get Final Score
        int no = QuestionController.getInstance().countCorrectAnswer();
        //Set Final Score
        Marks.setText(no+"/10");
    }


}