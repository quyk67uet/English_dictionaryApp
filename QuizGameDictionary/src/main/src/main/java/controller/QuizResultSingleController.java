package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.AnswerQ;
import model.Question;
import model.QuizQ;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.QuizController.questionsQ;


public class QuizResultSingleController implements Initializable {
    @FXML
    private Text question;

    @FXML
    private Label optionA;

    @FXML
    private Label optionB;
    @FXML
    private Label optionC;
    @FXML
    private Label optionD;

    public void setTexts(int i) {

        this.question.setText("Question " + (i + 1) + ": " + questionsQ.get(i).getQuestion());
        this.optionA.setText(questionsQ.get(i).getOptionA());
        this.optionB.setText(questionsQ.get(i).getOptionB());
        this.optionC.setText(questionsQ.get(i).getOptionC());
        this.optionD.setText(questionsQ.get(i).getOptionD());

        String[] listA = questionsQ.get(i).getAnswer().split(" ");

        switch (listA[2]) {
            case "A" -> {
                optionA.setTextFill(Color.web("#26ae60"));
                optionA.setText("✔ " + optionA.getText());
            }
            case "B" -> {
                optionB.setTextFill(Color.web("#26ae60"));
                optionB.setText("✔ " + optionB.getText());
            }
            case "C" -> {
                optionC.setTextFill(Color.web("#26ae60"));
                optionC.setText("✔ " + optionC.getText());
            }
            case "D" -> {
                optionD.setTextFill(Color.web("#26ae60"));
                optionD.setText("✔ " + optionD.getText());
            }
        }

        if (QuizController.getInstance().map.get(i) != null) {
            if (String.valueOf(optionA.getText().charAt(0)).equals(QuizController.getInstance().map.get(i))) {
                optionA.setTextFill(Color.web("#B83227"));
                optionA.setText("✖ " + optionA.getText());
            } else if (String.valueOf(optionB.getText().charAt(0)).equals(QuizController.getInstance().map.get(i))) {
                optionB.setTextFill(Color.web("#B83227"));
                optionB.setText("✖ " + optionB.getText());
            } else if (String.valueOf(optionC.getText().charAt(0)).equals(QuizController.getInstance().map.get(i))) {
                optionC.setTextFill(Color.web("#B83227"));
                optionC.setText("✖ " + optionC.getText());
            } else if (String.valueOf(optionD.getText().charAt(0)).equals(QuizController.getInstance().map.get(i))) {
                optionD.setTextFill(Color.web("#B83227"));
                optionD.setText("✖ " + optionD.getText());
            }
        } else {
            switch (listA[2]) {
                case "A" -> {
                    optionA.setTextFill(Color.web("#26ae60"));
                    optionA.setText("✔ " + optionA.getText());
                }
                case "B" -> {
                    optionB.setTextFill(Color.web("#26ae60"));
                    optionB.setText("✔ " + optionB.getText());
                }
                case "C" -> {
                    optionC.setTextFill(Color.web("#26ae60"));
                    optionC.setText("✔ " + optionC.getText());
                }
                case "D" -> {
                    optionD.setTextFill(Color.web("#26ae60"));
                    optionD.setText("✔ " + optionD.getText());
                }
            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}