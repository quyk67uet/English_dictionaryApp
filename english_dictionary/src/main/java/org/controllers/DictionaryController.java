package org.controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    
    @FXML
    private Tooltip tooltip1, tooltip2, tooltip3, tooltip4;

    @FXML
    private Button addButton, translateButton, searchButton, exitButton, gameButton;

    @FXML
    private AnchorPane container;

    @FXML
    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            setNode(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/SearchGraphical.fxml");
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/AdditionGraphical.fxml");
            }
        });

        translateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/TranslationGraphical.fxml");
            }
        });

        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/GameGraphical.fxml");
            }
        });

        tooltip1.setShowDelay(Duration.seconds(0.5));
        tooltip2.setShowDelay(Duration.seconds(0.5));
        tooltip3.setShowDelay(Duration.seconds(0.5));
        tooltip4.setShowDelay(Duration.seconds(0.5));
        showComponent("/scenes/SearchGraphical.fxml");

        // exitButton.setOnMouseClicked(e -> {
        //     System.exit(0);
        // });
    }

}
