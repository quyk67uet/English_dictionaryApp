package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizResultController implements Initializable {

    @FXML
    private Tooltip tooltipA;
    @FXML
    private JFXButton homeR_button;
    private static QuizResultController instance;

    public QuizResultController()
    {
        instance = this;
    }

    public static QuizResultController getInstance()
    {
        return instance;
    }
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

    @FXML
    void backToHome(ActionEvent event) throws IOException {

        homeR_button.getScene().getWindow().hide();

        Stage Result = new Stage();
        Result.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLViews/MenuView.fxml"));
        Scene scene = new Scene(root);
        Result.setScene(scene);
        Result.show();
        Result.setResizable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tooltipA.setShowDelay(Duration.seconds(0.5));
    }
}
