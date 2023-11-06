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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import org.features.*;

public class DictionaryController implements Initializable {
    // @FXML
    // private Tooltip addWordsTooltip, exitTooltip, gameTooltip, googleTranslateTooltip, aboutUsTooltip, searchTooltip;

    @FXML
    private Button addWordsButton, exitButton, gameButton, googleTranslateButton, bookmarkButton, searchButton;

    @FXML
    private AnchorPane container;

    private static Trie trie = new Trie();
    private static SQLite sqLite = new SQLite();
    private static MediaPlayer mediaPlayer;

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

    public void searchFeatures() {
        try {
            container.getChildren().clear();
            Node searchNode = new FXMLLoader(getClass().getResource("/scenes/SearchGraphical.fxml")).load();
            container.getChildren().add(searchNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void addWordFeatures() {
        try {
            container.getChildren().clear();
            Node addWordNode = new FXMLLoader(getClass().getResource("/scenes/AdditionGraphical.fxml")).load();
            container.getChildren().add(addWordNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookmarkFeatures() {
        try {
            container.getChildren().clear();
            Node addWordNode = new FXMLLoader(getClass().getResource("/scenes/BookmarkGraphical.fxml")).load();
            container.getChildren().add(addWordNode);
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

        // showTooltip();
        // exitButton.setOnMouseClicked(e -> {
        //     System.exit(0);
        // });
    }

}
