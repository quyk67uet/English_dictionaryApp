package org.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.features.*;

public class App extends Application {
   /* public static void main(String[] args) throws Exception {
        DictionaryCommandline commandline = new DictionaryCommandline();
        commandline.dictionaryAdvanced();

    }*/

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/DictionaryGUI.fxml"));
        stage.setTitle("Dictionary Application");
        stage.initStyle(StageStyle.TRANSPARENT);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}