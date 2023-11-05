package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.AnswerQ;
import model.QuizQ;


public class QuizController implements Initializable {


    private ToggleGroup TGroup;

    @FXML
    private JFXRadioButton RB1;

    @FXML
    private JFXRadioButton RB2;

    @FXML
    private JFXRadioButton RB3;

    @FXML
    private JFXRadioButton RB4;

    @FXML
    private JFXButton finish_button;

    @FXML
    private Text questions;

    String[][] qQ;
    String[][] aQ;

    public static int qQid;
    HashMap<Integer, String> map;
    public static String listAnswer;
    private static QuizController instance;

    public QuizController()
    {
        instance = this;
    }

    public static QuizController getInstance()
    {
        return instance;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        TGroup = new ToggleGroup();
        RB1.setToggleGroup(TGroup);
        RB2.setToggleGroup(TGroup);
        RB3.setToggleGroup(TGroup);
        RB4.setToggleGroup(TGroup);


        qQ=new String[10][5];
        aQ=new String[10][2];

        qQ = QuizQ.setQuizQ();
        aQ = AnswerQ.getAnswerQ();

        map = new HashMap<Integer, String>();

        renderQuestion(qQid);
    }

    public void renderQuestion(int i){
        try {
            if (i >= 0 && i < qQ.length) {
                questions.setText(qQ[i][0]);
                RB1.setText("A)  " + qQ[i][1]);
                RB2.setText("B)  " + qQ[i][2]);
                RB3.setText("C)  " + qQ[i][3]);
                RB4.setText("D)  " + qQ[i][4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String checkB = selectedToggle(i,RB1,RB2,RB3,RB4);

        if(Objects.equals(checkB, "b1"))
        {
            RB1.setSelected(true);
        }
        else if(Objects.equals(checkB, "b2"))
        {
            RB2.setSelected(true);
        }
        else if(Objects.equals(checkB, "b3"))
        {
            RB3.setSelected(true);
        }
        else if(Objects.equals(checkB, "b4"))
        {
            RB4.setSelected(true);
        }
        else
        {
            RB1.setSelected(false);
            RB2.setSelected(false);
            RB3.setSelected(false);
            RB4.setSelected(false);
        }

    }

    public void setQid(int i)
    {
        qQid =  i;

    }

    public int getQid()
    {
        return qQid;
    }

    public String selectedToggle(int questionQ, JFXRadioButton rb1, JFXRadioButton rb2, JFXRadioButton rb3, JFXRadioButton rb4)
    {
        String temp = map.get(questionQ);

        String selected = null;

        if(Objects.equals(temp, rb1.getText().substring(4)))
        {
            selected = "RB1";
        }
        else if(Objects.equals(temp, rb2.getText().substring(4)))
        {
            selected = "RB2";

        }
        else if(Objects.equals(temp, rb3.getText().substring(4)))
        {
            selected = "RB3";

        }
        else if(Objects.equals(temp, rb4.getText().substring(4)))
        {
            selected = "RB4";

        }

        return selected;

    }


    public String getSelection()
    {

        return listAnswer;
    }

    @FXML
    public void groupAction(ActionEvent event) {

        if(RB1.isSelected())
        {
            listAnswer = RB1.getText().substring(4);
        }
        else if(RB2.isSelected())
        {
            listAnswer = RB2.getText().substring(4);
        }
        else if(RB3.isSelected())
        {
            listAnswer = RB3.getText().substring(4);
        }
        else if(RB4.isSelected())
        {
            listAnswer= RB4.getText().substring(4);
        }


    }

    @FXML
    public void nextAction(ActionEvent e) throws IOException
    {
        if(qQid<10)
        {

            map.put(qQid,getSelection());
            if(Objects.equals(getSelection(), null))
            {
                TickBoxController.getInstance().setColorRed(qQid, true);
            }
            else
            {
                TickBoxController.getInstance().setColorGreen(qQid, true);
            }

            if(Objects.equals(qQid, 9))
            {
                renderQuestion(qQid);
                qQid++;
            }
            else
            {
                qQid++;
                listAnswer = null;
                renderQuestion(qQid);
            }


        }

        else {

            this.setDialogBox();
        }

    }

    @FXML
    public void previousAction(ActionEvent e) throws IOException {
        if (qQid > 0) {
            qQid--;

            map.put(qQid, getSelection());

            if (Objects.equals(getSelection(), null)) {
                TickBoxController.getInstance().setColorRed(qQid, true);
            } else {
                TickBoxController.getInstance().setColorGreen(qQid, true);
            }

            renderQuestion(qQid);
        }
    }


    public void setDialogBox() throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Good job!");
        String s = "Do you want to finish your Quiz Test?";
        alert.setContentText(s);

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/image/i18.png")));
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        alert.setGraphic(icon);

        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/StyleCSS/alert.css").toExternalForm()
        );

        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            finishFeatures();
            System.gc();
        } else {
            qQid--;
        }
    }


    public int countCorrectAnswer(){
        int qnum=10;
        int count=0;
        for(int qid=0;qid<qnum;qid++)
            if(aQ[qid][1].equals(map.get(qid)))
            {
                count++;
            }
        return count;
    }


    @FXML
    public void finishQuiz(ActionEvent event) throws IOException {
        this.setDialogBox();
    }

    public void finishFeatures() {
        try {
            qQid = 0;
            TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.2), MainController.getInstance().getAPane());
            slideOut.setToX(-MainController.getInstance().getAPane().getWidth());
            slideOut.setOnFinished(event -> {
                try {
                    MainController.getInstance().getAPane().getChildren().clear();
                    Node startNode = new FXMLLoader(getClass().getResource("/FXMLViews/PointView.fxml")).load();
                    MainController.getInstance().getAPane().getChildren().add(startNode);

                    TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.2), MainController.getInstance().getAPane());
                    slideIn.setFromX(MainController.getInstance().getAPane().getWidth());
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
}


