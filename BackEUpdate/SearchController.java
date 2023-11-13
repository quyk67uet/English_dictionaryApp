package org.controllers;

import org.features.*;

import com.jfoenix.controls.JFXButton;
import com.voicerss.tts.Languages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class SearchController implements Initializable {
    @FXML
    private JFXButton deleteTextFieldButton, editWordButton, soundButton, addBookmarkButton, deleteWordButton, submitButton;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private Label notFoundLable, editNotification;

    @FXML
    private WebView outputWebView;
    // private WebEngine webEngine = new WebView().getEngine();

    private DictionaryCommandline dictionaryCmd;
    private List<String> searchEnglishResult;
    private List<String> searchVietnameseResult;
    private List<String> historyResult;
    private static final String exportDirectory = "english_dictionary/src/main/resources/assets/ExportDictionary.txt";

    public void writeToFile(String filePath, String lineToRemove) throws FileNotFoundException, IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.equals(lineToRemove + " ")) {
                    // Write the line to the output file if it's not the line to remove
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            // Replace the original file with the modified file
            File originalFile = new File(filePath);
            File modifiedFile = new File("temp.txt");

            if (originalFile.delete() && modifiedFile.renameTo(originalFile)) {
                System.out.println("Line removed and file updated successfully.");
            } else {
                System.out.println("Failed to update the file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void writeHistoryToDatabase(String word) {
    //     DictionaryController.getSqLite().deleteHistoryDatabase(word);
    //     // System.out.println("Delete completed.");
    //     DictionaryController.getSqLite().insertHistoryDatabase(word);
    //     // System.out.println("Write completed.");
    // }

    public void loadWordtoTrie() {
        try {
            Task<Void> loadWordTask = new Task<>() {
                @Override
                protected Void call() throws Exception {

                    ResultSet wordDatabase = DictionaryController.getSqLite().getAllWordDatabase();
                    while (wordDatabase.next()) {
                        String word = wordDatabase.getString("word");
                        if (word != "null") DictionaryController.getTrie().insert(word);
                    }

                    return null;
                }
            };

            loadWordTask.setOnFailed(event -> {
                System.out.println("Failed to implement multithread");
            });
            loadWordTask.setOnSucceeded(event -> {
                System.out.println("Implemented successfully");
            });
            new Thread(loadWordTask).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputTextFieldEvent() {
        outputListView.getItems().clear();
        // outputTextArea.clear();
        notFoundLable.setVisible(false);
        outputWebView.getEngine().loadContent("");

        if (inputTextField.getText() == null || inputTextField.getText().trim().isEmpty()) {
            try {
                ResultSet wordDatabase = DictionaryController.getSqLite().getTableDatabase("history");
                while (wordDatabase.next()) {
                    String word = wordDatabase.getString("word");
                    if (!outputListView.getItems().contains(word)) outputListView.getItems().add(word);
                };
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String inputText = inputTextField.getText();
            if (inputTextField.getText() != null) {
                String[] wordQuery = DictionaryController.getTrie().printWordWithPrefix(inputText).split("\n");
                if (wordQuery.length == 1 && wordQuery[0].equals("No words with this prefix")) {
                    notFoundLable.setVisible(true);
                }
                else outputListView.getItems().addAll(wordQuery);
            }
        }
    }

    public void outputListViewEvent() {
        // outputTextArea.clear();
        editNotification.setVisible(false);
        outputWebView.getEngine().loadContent("");
        outputWebView.getEngine().executeScript("document.body.contentEditable = false;");

        String userSelected = outputListView.getSelectionModel().getSelectedItem();
        if (userSelected != null && !userSelected.equals("")) {
            // inputTextField.setText(inputTextField.getText() + userSelected);
            Task<Void> outputListViewTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    DictionaryController.getSqLite().deleteRowDatabase("history", userSelected);
                    DictionaryController.getSqLite().insertTableDatabase("history", userSelected);
                    return null;
                }
            };
            new Thread(outputListViewTask).start();
        }
        outputWebView.getEngine().loadContent(DictionaryController.getSqLite().wordProperty("html", userSelected));
        // webEngine.loadContent(DictionaryController.getSqLite().wordHTML(userSelected));

        // outputWebView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
        //     if (newState == Worker.State.SUCCEEDED) {
        //         String formattedHtmlContent = (String) outputWebView.getEngine().executeScript("document.documentElement.outerHTML");
        //         // outputTextArea.setText(formattedHtmlContent);
        //     }
        // });
    }

    public void outputTextAreaEvent() {
        // outputTextArea.scrollTopProperty().addListener((observable, oldValue, newValue) -> {
        //     if (newValue.doubleValue() > 0) {
        //         soundButton.setTranslateY(-newValue.doubleValue());
        //     } else {
        //         soundButton.setTranslateY(0);
        //     }
        // });
    }
    public void soundButtonEvent() {
        String userSelected = outputListView.getSelectionModel().getSelectedItem();
        try {
            Task<Void> soundTask = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    VoiceRSS.voiceSpeak(userSelected, Languages.English_GreatBritain);
                    DictionaryController.getMediaPlayer(VoiceRSS.audioPath).play();
                    return null;
                }

            };
            // TODO: Set visible label :"No words selected"
            soundTask.setOnFailed(event -> {
                String soundException = soundTask.getException().toString();
                if (soundException.substring(0, DictionaryController.langException.length()).equals(DictionaryController.langException)) {
                    System.out.println("no words selected");
                } else if (soundException.substring(0, DictionaryController.unknownHostException.length()).equals(DictionaryController.unknownHostException)) {
                    System.out.println("No internet connection");
                }
            });
            new Thread(soundTask).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addBookmarkButtonEvent() {
        // Alert confirm user request
        String userSelected = outputListView.getSelectionModel().getSelectedItem();

        if (userSelected != null && !userSelected.equals("")) {
            // inputTextField.setText(inputTextField.getText() + userSelected);
            Task<Void> addBookmarkTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    DictionaryController.getSqLite().deleteRowDatabase("bookmark", userSelected);
                    DictionaryController.getSqLite().insertTableDatabase("bookmark", userSelected);
                    return null;
                }
            };
            new Thread(addBookmarkTask).start();
        }
        // TODO: Add notification (label)
    }

    public void editWordButtonEvent() {
        // TODO: Add button save and alert to confirm user request
        editNotification.setVisible(false);
        String userSelected = outputListView.getSelectionModel().getSelectedItem();
        final StringBuilder editedText = new StringBuilder();
        outputWebView.getEngine().executeScript("document.body.contentEditable = true;");
        outputWebView.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String text = (String) outputWebView.getEngine().executeScript("document.body.innerHTML").toString();
                editedText.setLength(0);
                editedText.append(text);
            }
        });
        submitButton.setOnAction(event -> {
            Task<Void> submitTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    DictionaryController.getSqLite().updateWordDatabase("engvie", "html", userSelected, editedText.toString());
                    DictionaryController.getSqLite().updateWordDatabase("bookmark", "html", userSelected, editedText.toString());
                    DictionaryController.getSqLite().updateWordDatabase("history", "html", userSelected, editedText.toString());
                    editNotification.setVisible(true);
                    outputWebView.getEngine().executeScript("document.body.contentEditable = false;");
                    return null;
                }
            };
            new Thread(submitTask).start();
        });
    }

    public void deleteWordButtonEvent() {
        // TODO: Alert user

        String userSelected = outputListView.getSelectionModel().getSelectedItem();

        if (userSelected != null && !userSelected.equals("")) {
            // inputTextField.setText(inputTextField.getText() + userSelected);
            Task<Void> deleteWordTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    // DictionaryController.getSqLite().deleteRowDatabase("engvie", userSelected);
                    DictionaryController.getSqLite().deleteRowDatabase("history", userSelected);
                    DictionaryController.getSqLite().deleteRowDatabase("bookmark", userSelected);
                    return null;
                }
            };
            new Thread(deleteWordTask).start();
        }

        System.out.println("delete success");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // check có từ -> write đè lên lịch sử
        // load tất các từ trong word vào trie rồi tìm, tìm rồi lại từ word đó trả về query bên sqlite (poe)
        // Hỏi xem trong google translate có cho chọn ngôn ngữ hay không?`
        try {
            ResultSet wordDatabase = DictionaryController.getSqLite().getTableDatabase("history");
            while (wordDatabase.next()) {
                String word = wordDatabase.getString("word");
                if (!outputListView.getItems().contains(word)) outputListView.getItems().add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // outputTextArea.setEditable(false);
        outputWebView.getEngine().loadContent("");
        notFoundLable.setVisible(false);
        editNotification.setVisible(false);
        loadWordtoTrie();
        DictionaryController.getSqLite().createTableDatabase("history");
        DictionaryController.getSqLite().createTableDatabase("bookmark");


        outputListView.getSelectionModel().selectedItemProperty().addListener(event -> {
            outputListViewEvent();
        });

        inputTextField.textProperty().addListener(event -> {
            inputTextFieldEvent();
        });

        deleteTextFieldButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (!inputTextField.getText().equals("")) inputTextField.clear();
            }
        });

        editWordButton.setOnAction(event -> {
            editWordButtonEvent();
        });

        soundButton.setOnAction(event -> {
            soundButtonEvent();
        });

        addBookmarkButton.setOnAction(event -> {
            addBookmarkButtonEvent();
        });

        deleteWordButton.setOnAction(event -> {
            deleteWordButtonEvent();
        });
    }
}