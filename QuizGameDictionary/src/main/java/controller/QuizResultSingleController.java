package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.AnswerQ;
import model.QuizQ;

import java.net.URL;
import java.util.ResourceBundle;


public class QuizResultSingleController implements Initializable {
    @FXML
    private Label question;

    @FXML
    private Label option1;

    @FXML
    private Label option2;
    @FXML
    private Label option3;
    @FXML
    private Label option4;

    String[][] qQ;
    String[][] aQ;

    public void setTexts(int i){
        qQ=new String[10][5];
        aQ=new String[10][2];

        qQ = QuizQ.setQuizQ();
        aQ = AnswerQ.getAnswerQ();
        this.question.setText(qQ[i][0]);
        this.option1.setText("A)  " + qQ[i][1]);
        this.option2.setText("B)  " + qQ[i][2]);
        this.option3.setText("C)  " + qQ[i][3]);
        this.option4.setText("D)  " + qQ[i][4]);

        settingColors(0);
    }

    public void settingColors(int i) {
        Label rightAnswer = null;

        if(option1.getText().trim().equalsIgnoreCase(QuizController.getInstance().aQ[i][1])) {
            rightAnswer = option1;
        }
        else if(option2.getText().trim().equalsIgnoreCase(QuizController.getInstance().aQ[i][1])) {
            rightAnswer = option2;
        }
        else if(option3.getText().trim().equalsIgnoreCase(QuizController.getInstance().aQ[i][1])) {
            rightAnswer = option3;
        }
        else if(option4.getText().trim().equalsIgnoreCase(QuizController.getInstance().aQ[i][1])) {
            rightAnswer = option4;
        }

        rightAnswer.setTextFill(Color.web("#26ae60"));
        rightAnswer.setText("✔ "+rightAnswer.getText());


        if(!QuizController.getInstance().map.get(i).trim().equalsIgnoreCase(QuizController.getInstance().aQ[i][0])) {
            Label userAnswer = null;
            if(option1.getText().trim().equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                userAnswer = option1;
            }
            else if(option2.getText().trim().equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                userAnswer = option2;
            }

            else if(option3.getText().trim().equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                userAnswer = option3;
            }
            else if(option4.getText().trim().equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                userAnswer = option4;
            }

            userAnswer.setTextFill(Color.web("#B83227"));
            userAnswer.setText("✖ " + userAnswer.getText());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
