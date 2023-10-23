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

    // @FXML
    // public void setNode(Node node) {
    //     container.getChildren().clear();
    //     container.getChildren().add(node);
    // }

    // @FXML
    // public void showComponent(String path) {
    //     try {
    //         URL location = getClass().getResource(path);
    //         if (location == null) {
    //             System.out.println("Null detected!");
    //         }
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    //         AnchorPane component = loader.load();
    //         // loader.getController();
    //         setNode(component);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    @FXML
    public void searchFeatures() {
        try {
            container.getChildren().clear();
            Node searchNode = new FXMLLoader(getClass().getResource("/scenes/SearchGraphical.fxml")).load();
            container.getChildren().add(searchNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void translateFeatures() {
        try {
            container.getChildren().clear();
            Node translateNode = new FXMLLoader(getClass().getResource("/scenes/TranslationGraphical.fxml")).load();
            container.getChildren().add(translateNode);
            System.out.println("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addWordFeatures() {
        try {
            container.getChildren().clear();
            Node addWordNode = new FXMLLoader(getClass().getResource("/scenes/AdditionGraphical.fxml")).load();
            container.getChildren().add(addWordNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // @FXML
    // public void searchFeatures() {
    //     try {
    //         container.getChildren().clear();
    //         Node searchNode = new FXMLLoader(getClass().getResource("/scenes/SearchGraphical.fxml")).load();
    //         container.getChildren().add(searchNode);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    // @FXML
    // public void searchFeatures() {
    //     try {
    //         container.getChildren().clear();
    //         Node searchNode = new FXMLLoader(getClass().getResource("/scenes/SearchGraphical.fxml")).load();
    //         container.getChildren().add(searchNode);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    // @FXML
    // public void searchFeatures() {
    //     try {
    //         container.getChildren().clear();
    //         Node searchNode = new FXMLLoader(getClass().getResource("/scenes/SearchGraphical.fxml")).load();
    //         container.getChildren().add(searchNode);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchButton.setOnAction(event -> {
            searchFeatures();
        });
        googleTranslateButton.setOnAction(event -> {
            translateFeatures();
        });
        addWordsButton.setOnAction(event -> {
            addWordFeatures();
        });

        addWordsTooltip.setShowDelay(Duration.seconds(0.5));
        exitTooltip.setShowDelay(Duration.seconds(0.5));
        gameTooltip.setShowDelay(Duration.seconds(0.5));
        googleTranslateTooltip.setShowDelay(Duration.seconds(0.5));
        aboutUsTooltip.setShowDelay(Duration.seconds(0.5));
        searchTooltip.setShowDelay(Duration.seconds(0.5));

        // exitButton.setOnMouseClicked(e -> {
        //     System.exit(0);
        // });
    }

}
