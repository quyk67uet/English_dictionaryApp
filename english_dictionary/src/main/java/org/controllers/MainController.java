package org.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

import org.features.dictionary.*;
import org.features.game.*;

public class MainController implements Initializable {
    // @FXML
    // private Tooltip addWordsTooltip, exitTooltip, gameTooltip, googleTranslateTooltip, aboutUsTooltip, searchTooltip;

    @FXML
    private Button addWordsButton, homeButton, gameButton, googleTranslateButton, bookmarkButton, searchButton;

    @FXML
    private AnchorPane container;

    private static Trie trie = new Trie();
    private static SQLite sqLite = new SQLite();
    private static MediaPlayer mediaPlayer;
    private static AlertManagement alertManagement = new AlertManagement();

    public static SQLite getSqLite() {
        return sqLite;
    }

    public static Trie getTrie() {
        return trie;
    }

    public static MediaPlayer getMediaPlayer(String path) {
        mediaPlayer = new MediaPlayer(new Media(Paths.get(path).toUri().toString()));
        return mediaPlayer;
    }

    public static AlertManagement getAlertManagement() {
        return alertManagement;
    }
    

    public static final String langException = "java.lang.Exception";
    public static final String unknownHostException = "java.net.UnknownHostException";
    
    public void searchFeatures() {
        try {
            Stage stage = (Stage) searchButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/SearchGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void translateFeatures() {
        try {
            Stage stage = (Stage) googleTranslateButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/TranslationGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWordFeatures() {
        try {
            Stage stage = (Stage) addWordsButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/AdditionGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookmarkFeatures() {
        try {
            Stage stage = (Stage) bookmarkButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/BookmarkGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeFeatures() {
        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/HomeGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameFeatures() {
        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/GameGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void showTooltip() {
    //     addWordsTooltip.setShowDelay(Duration.seconds(0.5));
    //     exitTooltip.setShowDelay(Duration.seconds(0.5));
    //     gameTooltip.setShowDelay(Duration.seconds(0.5));
    //     googleTranslateTooltip.setShowDelay(Duration.seconds(0.5));
    //     aboutUsTooltip.setShowDelay(Duration.seconds(0.5));
    //     searchTooltip.setShowDelay(Duration.seconds(0.5));
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

        bookmarkButton.setOnAction(event -> {
            bookmarkFeatures();
        });

        homeButton.setOnAction(event -> {
            homeFeatures();
        });

        gameButton.setOnAction(event -> {
            gameFeatures();
        });
        // showTooltip();
        // exitButton.setOnMouseClicked(e -> {
        //     System.exit(0);
        // });
    }

}
