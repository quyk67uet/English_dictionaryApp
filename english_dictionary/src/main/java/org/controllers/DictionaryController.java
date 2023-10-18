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
    private Tooltip addWordsTooltip, exitTooltip, gameTooltip, googleTranslateTooltip, aboutUsTooltip, searchTooltip;

    @FXML
    private Button addWordsButton, exitButton, gameButton, googleTranslateButton, infoButton, searchButton;

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

        googleTranslateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/TranslationGraphical.fxml");
            }
        });
        
        addWordsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/AdditionGraphical.fxml");
            }
        });

        

        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/GameGraphical.fxml");
            }
        });

        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/scenes/AboutUsGraphical.fxml");
            }
        });

        addWordsTooltip.setShowDelay(Duration.seconds(0.5));
        exitTooltip.setShowDelay(Duration.seconds(0.5));
        gameTooltip.setShowDelay(Duration.seconds(0.5));
        googleTranslateTooltip.setShowDelay(Duration.seconds(0.5));
        aboutUsTooltip.setShowDelay(Duration.seconds(0.5));
        searchTooltip.setShowDelay(Duration.seconds(0.5));
        showComponent("/scenes/SearchGraphical.fxml");

        // exitButton.setOnMouseClicked(e -> {
        //     System.exit(0);
        // });
    }

}
