package org.controllers.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controllers.MainController;
import org.features.game.*;


public abstract class QuestionController {

    protected static int questionNumber = 10;
    protected static ArrayList<Question> questionList = new ArrayList<>();
    protected static ArrayList<Question> questionsQ = new ArrayList<>();

    protected ToggleGroup TGroup;

    @FXML
    protected JFXRadioButton optionA;

    @FXML
    protected JFXRadioButton optionB;

    @FXML
    protected JFXRadioButton optionC;

    @FXML
    protected JFXRadioButton optionD;

    @FXML
    protected JFXButton finish_button;

    @FXML
    protected Text question;

    public static int qQid;

    public HashMap<Integer, String> map = new HashMap<>();

    public static String choice;
    private static QuestionController instance;
    public QuestionController()
    {
        instance = this;
    }

    public static QuestionController getInstance()
    {
        return instance;
    }

    public void renderQuestion(int i){
        try {
            if (i >= 0 && i < questionsQ.size()) {
                question.setText("Question " + (i + 1) + ": " + questionsQ.get(i).getQuestion());
                optionA.setText(questionsQ.get(i).getOptionA());
                optionB.setText(questionsQ.get(i).getOptionB());
                optionC.setText(questionsQ.get(i).getOptionC());
                optionD.setText(questionsQ.get(i).getOptionD());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String checkB = selectedToggle(i);

        if (checkB != null) {
            if(String.valueOf(optionA.getText().charAt(0)).equals(checkB)) {
                optionA.setSelected(true);
            } else if (String.valueOf(optionB.getText().charAt(0)).equals(checkB)) {
                optionB.setSelected(true);
            } else if (String.valueOf(optionC.getText().charAt(0)).equals(checkB)) {
                optionC.setSelected(true);
            } else if (String.valueOf(optionD.getText().charAt(0)).equals(checkB)) {
                optionD.setSelected(true);
            }
        }
        else {
            optionA.setSelected(false);
            optionB.setSelected(false);
            optionC.setSelected(false);
            optionD.setSelected(false);
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

    public String selectedToggle(int questionQ) {
        return map.get(questionQ);
    }

    public String getSelection()
    {
        return choice;
    }

    @FXML
    public void groupAction(ActionEvent event) {

        if(optionA.isSelected())
        {
            choice = String.valueOf(optionA.getText().charAt(0));
            map.put(qQid, getSelection());
            TickBoxController.getInstance().setColorGreen(qQid, true);
        }
        else if(optionB.isSelected())
        {
            choice = String.valueOf(optionB.getText().charAt(0));
            map.put(qQid, getSelection());
            TickBoxController.getInstance().setColorGreen(qQid, true);
        }
        else if(optionC.isSelected())
        {
            choice = String.valueOf(optionC.getText().charAt(0));
            map.put(qQid, getSelection());
            TickBoxController.getInstance().setColorGreen(qQid, true);
        }
        else if(optionD.isSelected())
        {
            choice = String.valueOf(optionD.getText().charAt(0));
            map.put(qQid, getSelection());
            TickBoxController.getInstance().setColorGreen(qQid, true);
        }

    }

    @FXML
    public void nextAction(ActionEvent e) throws IOException {
        map.put(qQid, getSelection());

        if (!Objects.equals(getSelection(), null)) {
            TickBoxController.getInstance().setColorGreen(qQid, true);
        }

        if (Objects.equals(qQid, 9)) {
            renderQuestion(qQid);
        } else {
            qQid++;
            renderQuestion(qQid);
            // Retrieve the selected answer for the next question
            choice = map.get(qQid);
        }
     }

    @FXML
    public void previousAction(ActionEvent e) throws IOException {
        if (qQid > 0) {
            map.put(qQid, getSelection());

            qQid--;

            renderQuestion(qQid);

            choice = map.get(qQid);
        }
    }


    public void setDialogBox() throws IOException {
        Alert alert = MainController.getAlertManagement().showAlert(Alert.AlertType.CONFIRMATION, "Notification", "Do you want to finish your Quiz Test?");
        alert.setHeaderText("Good job!");
        String s = "Do you want to finish your Quiz Test?";
        alert.setContentText(s);

        if (alert.showAndWait().get().getButtonData() == ButtonBar.ButtonData.YES) {
            finishFeatures();
            System.gc();
        }
        else qQid--;
    }


    public int countCorrectAnswer() {
        int count = 0;
        for (int qid = 0; qid < questionNumber; qid++) {
            String[] listA = questionsQ.get(qid).getAnswer().split(" ");
            if (map.get(qid) != null) {
                if (map.get(qid).equals(listA[2])) {
                    ++count;
                }
            }
        }
        return count;
    }


    @FXML
    public void finishQuiz(ActionEvent event) throws IOException {
        this.setDialogBox();
    }

    public void finishFeatures() throws IOException {
        Stage stage = (Stage) finish_button.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/PointView.fxml")));
        stage.getScene().setRoot(root);
        stage.show();
    }
    public abstract void getQuizData();

    public void resetStatus() {
        map = new HashMap<>();
        questionList.clear();
        questionsQ.clear();
        qQid = 0;
        choice = null;
    }

}

