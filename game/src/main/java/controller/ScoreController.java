package controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
            ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.2), containerP);
            scaleOut.setToX(0);
            scaleOut.setToY(0);
            scaleOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) back_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXMLViews/GameGraphical.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

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
                    Stage stage = (Stage) result_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXMLViews/QuizResult.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

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
        int no = QuestionController.getInstance().countCorrectAnswer();
        //Set Final Score
        Marks.setText(no+"/10");
    }


}
