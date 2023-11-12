package org.controllers;

import com.jfoenix.controls.JFXButton;
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

import org.features.*;

public class DictionaryController implements Initializable {
     @FXML
    private JFXButton addButton,homeButton, transButton, searchButton, bookMButton, gameButton;

    private static Trie trie = new Trie();
    private static SQLite sqLite = new SQLite();
    private static MediaPlayer mediaPlayer;

    public static final String langException = "java.lang.Exception";
    public static final String unknownHostException = "java.net.UnknownHostException";

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
            Stage stage = (Stage) transButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/TranslationGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWordFeatures() {
        try {
            Stage stage = (Stage) addButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/AdditionGraphical.fxml")));
            stage.getScene().setRoot(root);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookmarkFeatures() {
        try {
            Stage stage = (Stage) bookMButton.getScene().getWindow();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchButton.setOnAction(event -> {
            searchFeatures();
        });
        transButton.setOnAction(event -> {
            translateFeatures();
        });

        addButton.setOnAction(event -> {
            addWordFeatures();
        });
        bookMButton.setOnAction(event -> {
            bookmarkFeatures();
        });
        homeButton.setOnAction(event -> {
            homeFeatures();
        });

    }

}