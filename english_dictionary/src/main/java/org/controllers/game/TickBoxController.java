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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.features.game.CountDown;


public class TickBoxController implements Initializable {

    @FXML
    private JFXButton buttonV, buttonG, homeQ_button;
    @FXML
    private BorderPane containerQ;

    BorderPane homeV;

    @FXML
    private VBox quizView;
    @FXML
    private Tooltip tooltipE;
    @FXML
    private JFXButton q1;

    @FXML
    private JFXButton q4;

    @FXML
    private JFXButton q3;

    @FXML
    private JFXButton q2;

    @FXML
    private JFXButton q5;

    @FXML
    private JFXButton q6;

    @FXML
    private JFXButton q7;

    @FXML
    private JFXButton q8;

    @FXML
    private JFXButton q9;

    @FXML
    private JFXButton q10;

    @FXML
    private HBox countDown;

    private Timeline timeline;

    private static final Integer STARTTIME = 600;

    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    private static TickBoxController instance;


    public TickBoxController()
    {
        instance = this;
    }


    public static TickBoxController getInstance()
    {
        return instance;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        CountDown count = new CountDown();
        buttonV.setOnAction(event -> {
            this.createPage(homeV, "/scenes/VocabularyView.fxml");
            countDown.getChildren().clear();
            countDown.getChildren().add(count.setCountdown());
            resetTickBox();
        });

        buttonG.setOnAction(event -> {
            this.createPage(homeV, "/scenes/GrammarView.fxml");
            countDown.getChildren().clear();
            countDown.getChildren().add(count.setCountdown());
            resetTickBox();
        });

        tooltipE.setShowDelay(Duration.seconds(0.5));
        homeQ_button.setOnAction(event -> {
            backQFeatures();
        });
    }

    public void setNode(Node node)
    {
        quizView.getChildren().clear();
        quizView.getChildren().add((Node) node);

    }

    public void createPage(BorderPane questionArea,String QPath) {

        try {
            questionArea = FXMLLoader.load(getClass().getResource(QPath));
            setNode(questionArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void q1Action(ActionEvent event) {


        QuestionController.getInstance().renderQuestion(0);
        QuestionController.getInstance().setQid(0);


    }

    @FXML
    void q2Action(ActionEvent event) {

        QuestionController.getInstance().renderQuestion(1);
        QuestionController.getInstance().setQid(1);


    }

    @FXML
    void q3Action(ActionEvent event) {


        QuestionController.getInstance().renderQuestion(2);
        QuestionController.getInstance().setQid(2);

    }

    @FXML
    void q4Action(ActionEvent event) {

        QuestionController.getInstance().renderQuestion(3);
        QuestionController.getInstance().setQid(3);

    }

    @FXML
    void q5Action(ActionEvent event) {

        QuestionController.getInstance().renderQuestion(4);
        QuestionController.getInstance().setQid(4);

    }

    @FXML
    void q6Action(ActionEvent event) {

        QuestionController.getInstance().renderQuestion(5);
        QuestionController.getInstance().setQid(5);

    }

    @FXML
    void q7Action(ActionEvent event) {

        QuestionController.getInstance().renderQuestion(6);
        QuestionController.getInstance().setQid(6);

    }

    @FXML
    void q8Action(ActionEvent event) {

        QuestionController.getInstance().renderQuestion(7);
        QuestionController.getInstance().setQid(7);

    }

    @FXML
    void q9Action(ActionEvent event) {
        QuestionController.getInstance().renderQuestion(8);
        QuestionController.getInstance().setQid(8);

    }

    @FXML
    void q10Action(ActionEvent event) {
        QuestionController.getInstance().renderQuestion(9);
        QuestionController.getInstance().setQid(9);
    }

    public void setColorGreen(int qQid , boolean value)
    {
        if(Objects.equals(value, true))
        {
            if(Objects.equals(qQid, 0))
            {
                q1.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");

            }
            else if(Objects.equals(qQid, 1))
            {
                q2.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 2))
            {
                q3.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 3))
            {
                q4.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 4))
            {
                q5.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 5))
            {
                q6.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 6))
            {
                q7.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 7))
            {
                q8.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 8))
            {
                q9.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 9))
            {
                q10.setStyle("-fx-background-color: #29a827; -fx-text-fill: white;");
            }

        }

    }

    public void backQFeatures() {
        try {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), containerQ);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) homeQ_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/GameGraphical.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), containerQ);
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

    public void resetTickBox() {
        q1.setStyle("-fx-background-color: #F0F8FF;");
        q2.setStyle("-fx-background-color: #F0F8FF; ");
        q3.setStyle("-fx-background-color: #F0F8FF; ");
        q4.setStyle("-fx-background-color: #F0F8FF; ");
        q5.setStyle("-fx-background-color: #F0F8FF;");
        q6.setStyle("-fx-background-color: #F0F8FF; ");
        q7.setStyle("-fx-background-color: #F0F8FF; ");
        q8.setStyle("-fx-background-color: #F0F8FF; ");
        q9.setStyle("-fx-background-color: #F0F8FF; ");
        q10.setStyle("-fx-background-color: #F0F8FF; ");
    }

}