package org.controllers.game;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class QuizResultController implements Initializable {
    @FXML
    private BorderPane containerO;
    @FXML
    private Tooltip tooltipA;
    @FXML
    private JFXButton homeR_button;
    private static QuizResultController instance;

    public QuizResultController()
    {
        instance = this;
    }

    public static QuizResultController getInstance()
    {
        return instance;
    }
    @FXML
    private VBox questionContainer;
    public void renderQuestionsResult() {
        for (int i = 0; i < QuestionController.questionsQ.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/QuizResultSingleQ.fxml"));
            try {
                Node node = fxmlLoader.load();
                QuizResultSingleController controller = fxmlLoader.getController();
                controller.setTexts(i);
                questionContainer.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeR_button.setOnAction(event -> {
            backRFeatures();
        });
        tooltipA.setShowDelay(Duration.seconds(0.5));
    }

    public void backRFeatures() {
        try {
            QuestionController.qQid = 0;
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), containerO);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) homeR_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/GameGraphical.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), containerO);
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
}