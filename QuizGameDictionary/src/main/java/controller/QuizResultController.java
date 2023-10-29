package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizResultController implements Initializable {

    @FXML
    private VBox questionContainer;
    public void renderQuestionsResult() {
        for (int i = 0; i < QuizController.getInstance().qQ.length; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLViews/QuizResultSingleQ.fxml"));
            try {
                Node node = fxmlLoader.load();
                QuizResultSingleController controller = fxmlLoader.getController();
                controller.setTexts(i);
                questionContainer.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
