package org.controllers.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
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
import org.features.game.*;

public class GrammarController extends QuestionController implements Initializable {
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TGroup = new ToggleGroup();
        optionA.setToggleGroup(TGroup);
        optionB.setToggleGroup(TGroup);
        optionC.setToggleGroup(TGroup);
        optionD.setToggleGroup(TGroup);

        resetStatus();
        getQuizData();
        renderQuestion(qQid);
    }

    @Override
    public void getQuizData() {
        if (!questionList.isEmpty()) return;
        File file = new File("english_dictionary\\src\\main\\resources\\assets\\datas\\questions.txt");

            // Task<Void> grammarTask = new Task<Void>() {
            //     @Override
            //     protected Void call() throws Exception {
            //         try {
                        
            //         } catch (Exception e) {
            //             e.printStackTrace();
            //         }
            //         return null;
            //     }
            // };
            // grammarTask.setOnFailed(event -> {
            //     System.out.println(grammarTask.getException().getMessage());
            // });
            // grammarTask.setOnSucceeded(event -> {
            //     System.out.println("Success.");
            // });
            // new Thread(grammarTask).start();
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
            List<String> option = new ArrayList<>();
            String question;
            if (fileScanner.hasNextLine()) {
                question = fileScanner.nextLine();
            } else {
                question = "";
            }
            if (fileScanner.hasNextLine()) {
                option.add(fileScanner.nextLine());
            } else {
                option.add("");
            }

            if (fileScanner.hasNextLine()) {
                option.add(fileScanner.nextLine());
            } else {
                option.add("");
            }

            if (fileScanner.hasNextLine()) {
                option.add(fileScanner.nextLine());
            } else {
                option.add("");
            }
            
            if (fileScanner.hasNextLine()) {
                option.add(fileScanner.nextLine());
            } else {
                option.add("");
            }

            String answer;
            if (fileScanner.hasNextLine()) {
                answer = fileScanner.nextLine();
            } else {
                answer = "";
            }
            questionList.add(new Question(question, option.get(0), option.get(1), option.get(2), option.get(3), answer));
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        Collections.shuffle(questionList);
        for (int i = 0; i < Math.min(questionList.size(), 10); i++) {
            questionsQ.add(questionList.get(i));
        }
    }
}