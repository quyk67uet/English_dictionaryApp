package org.main;

import java.io.*;
import java.net.URL;

import javafx.scene.image.Image;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/HomeGraphical.fxml"));
            Scene scene = new Scene(root);
            Image image = new Image("/assets/images/computer.png");

            stage.getIcons().add(image);
            stage.setTitle("Dictionary Apps");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setMinWidth(1026);
            stage.setMinHeight(625);
            
            stage.show();

        } catch (FileNotFoundException e) {
            System.out.println("File not found, please re-check the file directory");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Input - output wrong data types");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Err");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}