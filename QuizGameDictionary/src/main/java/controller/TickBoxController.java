package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.CountDown;


public class TickBoxController implements Initializable {

    @FXML
    private Tooltip tooltipE;
    @FXML
    private JFXButton homeQ_button;
    @FXML
    private AnchorPane quizView;

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
    private AnchorPane countDown;

    private Timeline timeline;
    AnchorPane homeV;
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

        this.createPage(homeV, "/FXMLViews/QuestionViews.fxml");
        CountDown count = new CountDown();

        countDown.getChildren().add(count.setCountdown());

        tooltipE.setShowDelay(Duration.seconds(0.5));
    }


    //Timer
    public void setTimer()
    {

        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();

    }

    public void setNode(Node node)
    {
        quizView.getChildren().clear();
        quizView.getChildren().add((Node) node);

    }

    public void setLabelTimer(String timer)
    {
        System.out.println(timer);
    }


    public void createPage(AnchorPane questionArea,String QPath) {

        try {
            questionArea = FXMLLoader.load(getClass().getResource(QPath));
            setNode(questionArea);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    @FXML
    public void q1Action(ActionEvent event) {


        QuizController.getInstance().renderQuestion(0);
        QuizController.getInstance().setQid(0);


    }

    @FXML
    void q2Action(ActionEvent event) {

        QuizController.getInstance().renderQuestion(1);
        QuizController.getInstance().setQid(1);


    }

    @FXML
    void q3Action(ActionEvent event) {


        QuizController.getInstance().renderQuestion(2);
        QuizController.getInstance().setQid(2);

    }

    @FXML
    void q4Action(ActionEvent event) {

        QuizController.getInstance().renderQuestion(3);
        QuizController.getInstance().setQid(3);

    }

    @FXML
    void q5Action(ActionEvent event) {

        QuizController.getInstance().renderQuestion(4);
        QuizController.getInstance().setQid(4);

    }

    @FXML
    void q6Action(ActionEvent event) {

        QuizController.getInstance().renderQuestion(5);
        QuizController.getInstance().setQid(5);

    }

    @FXML
    void q7Action(ActionEvent event) {

        QuizController.getInstance().renderQuestion(6);
        QuizController.getInstance().setQid(6);

    }

    @FXML
    void q8Action(ActionEvent event) {

        QuizController.getInstance().renderQuestion(7);
        QuizController.getInstance().setQid(7);

    }

    @FXML
    void q9Action(ActionEvent event) {


        QuizController.getInstance().renderQuestion(8);
        QuizController.getInstance().setQid(8);

    }

    @FXML
    void q10Action(ActionEvent event) {


        QuizController.getInstance().renderQuestion(9);
        QuizController.getInstance().setQid(9);

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

    public void setColorRed(int qQid, boolean value)
    {
        if(Objects.equals(value, true))
        {
            if(Objects.equals(qQid, 0))
            {
                q1.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");

            }
            else if(Objects.equals(qQid, 1))
            {
                q2.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 2))
            {
                q3.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 3))
            {
                q4.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 4))
            {
                q5.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 5))
            {
                q6.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 6))
            {
                q7.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 7))
            {
                q8.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 8))
            {
                q9.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }
            else if(Objects.equals(qQid, 9))
            {
                q10.setStyle("-fx-background-color: #eb3b3b; -fx-text-fill: white;");
            }

        }

    }

    @FXML
    void goToHomeQ(ActionEvent event) throws IOException {

        homeQ_button.getScene().getWindow().hide();

        Stage Result = new Stage();
        Result.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLViews/MenuView.fxml"));
        Scene scene = new Scene(root);
        Result.setScene(scene);
        Result.show();
        Result.setResizable(false);

    }

}
