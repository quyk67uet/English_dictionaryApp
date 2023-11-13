package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static controller.QuizController.questionsQ;

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
        for (int i = 0; i < questionsQ.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLViews/QuizResultSingleQ.fxml"));
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
            ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.2), containerO);
            scaleOut.setToX(0);
            scaleOut.setToY(0);
            scaleOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) homeR_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXMLViews/MenuView.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

                    ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.2), containerO);
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
}