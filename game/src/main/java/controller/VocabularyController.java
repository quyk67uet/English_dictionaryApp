package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import model.Question;

public class VocabularyController extends QuestionController implements Initializable {

    @Override
    public void getQuizData() {
        try {
            
            // Task<Void> vocabularyTask = new Task<Void>() {
            //     @Override
            //     protected Void call() throws Exception {

            //         return null;
            //     }
            // };
            // for (int i = 0; i < questionList.size(); i++) {
            //     System.out.println(questionList.get(i));
            // }
            // vocabularyTask.setOnFailed(event -> {
            //     System.out.println(vocabularyTask.getException().getMessage());
            // });
            
            int questionCount = 0;
            while (questionCount < questionNumber) {
                List<String> option = new ArrayList<>();
                ResultSet bookmarkDatabase = GameController.getSqLite().getTableDatabase("bookmark", 1);
                String question = "Từ nào sau đây tương ứng với nghĩa của từ: ";
                while (bookmarkDatabase.next()) {
                    question += bookmarkDatabase.getString("description");

                    option.add(bookmarkDatabase.getString("word"));
                }
                
                String condition = "word != \'" + option.get(0) + "\'";
                ResultSet wordDatabase = GameController.getSqLite().getTableDatabase("engvie", condition, 3);
                while (wordDatabase.next()) {
                    option.add(wordDatabase.getString("word"));
                }
                String answer = option.get(0);
                Collections.shuffle(option);
                for (int i = 0; i < option.size(); i++) {
                    if (answer.equals(option.get(i))) {
                        answer = "Đáp án: " + (char) ('A' + i);
                    }
                }
                questionList.add(new Question(question, "A. " + option.get(0), "B. " + option.get(1), "C. " + option.get(2), "D. " + option.get(3), answer));
                questionCount++;
            }
            Collections.shuffle(questionList);
            for (int i = 0; i < Math.min(questionList.size(), 10); i++) {
                questionsQ.add(questionList.get(i));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TGroup = new ToggleGroup();
        optionA.setToggleGroup(TGroup);
        optionB.setToggleGroup(TGroup);
        optionC.setToggleGroup(TGroup);
        optionD.setToggleGroup(TGroup);

        resetStatus();
        getQuizData();
        // TODO: Waiting
        renderQuestion(qQid);

    }  
}
