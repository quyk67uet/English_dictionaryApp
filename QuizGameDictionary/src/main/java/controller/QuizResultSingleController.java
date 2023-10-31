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
        this.option1.setText("A) " + qQ[i][1]);
        this.option2.setText("B) " + qQ[i][2]);
        this.option3.setText("C) " + qQ[i][3]);
        this.option4.setText("D) " + qQ[i][4]);

        if(option1.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
            option1.setTextFill(Color.web("#26ae60"));
            option1.setText("✔ "+option1.getText());
        }
        else if(option2.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
            option2.setTextFill(Color.web("#26ae60"));
            option2.setText("✔ "+option2.getText());
        }
        else if(option3.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
            option3.setTextFill(Color.web("#26ae60"));
            option3.setText("✔ "+option3.getText());
        }
        else if(option4.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
            option4.setTextFill(Color.web("#26ae60"));
            option4.setText("✔ "+option4.getText());
        }

        if(QuizController.getInstance().map.get(i) != null) {
            if(!QuizController.getInstance().map.get(i).substring(4).equalsIgnoreCase(aQ[i][0])) {
                if(option1.getText().substring(3).equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                    option1.setTextFill(Color.web("#B83227"));
                    option1.setText("✖ " + option1.getText());
                }
                else if(option2.getText().substring(3).equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                    option2.setTextFill(Color.web("#B83227"));
                    option2.setText("✖ " + option2.getText());
                }

                else if(option3.getText().substring(3).equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                    option3.setTextFill(Color.web("#B83227"));
                    option3.setText("✖ " + option3.getText());
                }
                else if(option4.getText().substring(3).equalsIgnoreCase(QuizController.getInstance().map.get(i))) {
                    option4.setTextFill(Color.web("#B83227"));
                    option4.setText("✖ " + option4.getText());
                }

            }
        }
        else {
            if(option1.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
                option1.setTextFill(Color.web("#26ae60"));
                option1.setText("✔ "+option1.getText());
            }
            else if(option2.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
                option2.setTextFill(Color.web("#26ae60"));
                option2.setText("✔ "+option2.getText());
            }
            else if(option3.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
                option3.setTextFill(Color.web("#26ae60"));
                option3.setText("✔ "+option3.getText());
            }
            else if(option4.getText().substring(3).equalsIgnoreCase(aQ[i][1])) {
                option4.setTextFill(Color.web("#26ae60"));
                option4.setText("✔ "+option4.getText());
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
