package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage;
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLViews/MenuView.fxml"));
            Scene scene = new Scene(root);
            Image image = new Image("/image/computer2.png");

            stage.getIcons().add(image);
            stage.setTitle("Dictionary Quiz");

            //stage.initStyle(StageStyle.UNDECORATED);

            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }


}
