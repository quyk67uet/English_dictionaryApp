package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScoreController implements Initializable{

    @FXML
    private Label Marks;
    @FXML
    private JFXButton result_button;
    @FXML
    private JFXButton back_button;

    private static ScoreController instance;

    public ScoreController()
    {
        instance = this;
    }

    public static ScoreController getInstance()
    {
        return instance;
    }

    @FXML
    void backToMenu(ActionEvent event) throws IOException {

        back_button.getScene().getWindow().hide();

        Stage Result = new Stage();
        Result.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLViews/MenuView.fxml"));
        Scene scene = new Scene(root);
        Result.setScene(scene);
        Result.show();
        Result.setResizable(false);

    }

    @FXML
    void goToResult(ActionEvent event) throws IOException {
        result_button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLViews/QuizResult.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage resultStage = new Stage();
        resultStage.initStyle(StageStyle.UNDECORATED);
        resultStage.setScene(scene);
        resultStage.show();
        resultStage.setResizable(false);

        QuizResultController.getInstance().renderQuestionsResult();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Get Final Score
        int no = QuizController.getInstance().countCorrectAnswer();
        //Set Final Score
        Marks.setText(no+"/10");
    }


}
