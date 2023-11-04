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
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private JFXButton deleteTextFieldButton, editWordButton, soundButton, addBookmarkButton, deleteWordButton;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private Label notFoundLable;

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
            ResultSet wordDatabase = DictionaryController.getSqLite().getAllWordDatabase();
            while (wordDatabase.next()) {
                String word = wordDatabase.getString("word");
                DictionaryController.getTrie().insert(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputTextFieldEvent() {
        outputListView.getItems().clear();
        outputTextArea.clear();
        notFoundLable.setVisible(false);
        if (inputTextField.getText() == null || inputTextField.getText().trim().isEmpty()) {
            try {
                ResultSet wordDatabase = DictionaryController.getSqLite().getTableDatabase("history");
                while (wordDatabase.next()) {
                    String word = wordDatabase.getString("word");
                    outputListView.getItems().add(word);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            String[] wordQuery = DictionaryController.getTrie().printWordWithPrefix(inputTextField.getText()).split("\n");
            if (wordQuery.length == 1 && wordQuery[0].equals("No words with this prefix")) {
                notFoundLable.setVisible(true);
            }
            else outputListView.getItems().addAll(wordQuery);
            
        }
    }

    public void outputListViewEvent() {
        outputTextArea.clear();
        // webEngine.loadContent(outputTextArea.textProperty().get());
        String userSelected = outputListView.getSelectionModel().getSelectedItem();
        if (userSelected != null) {
            DictionaryController.getSqLite().deleteRowDatabase("history", userSelected);
            DictionaryController.getSqLite().insertTableDatabase("history", userSelected);
        }
        // webEngine.loadContent(DictionaryController.getSqLite().wordHTML(userSelected));
        outputTextArea.textProperty().set(DictionaryController.getSqLite().wordProperty("html", userSelected));
    }

    public void soundButtonEvent() {
        try {
            String userSelected = outputListView.getSelectionModel().getSelectedItem();
            VoiceRSS.voiceSpeak(userSelected, Languages.English_GreatBritain);
            DictionaryController.getMediaPlayer(VoiceRSS.audioPath).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public void addBookmarkButtonEvent() {
        // Alert confirm user request
        String userSelected = outputListView.getSelectionModel().getSelectedItem();
        DictionaryController.getSqLite().deleteRowDatabase("bookmark", userSelected);
        DictionaryController.getSqLite().insertTableDatabase("bookmark", userSelected);
        System.out.println("add bookmark comleted");
    }

    public void editWordButtonEvent() {
        outputTextArea.setEditable(true);
        // TODO: Add button save and alert to confirm user request

    }

    public void deleteWordButtonEvent() {
        // TODO: Alert user
        String userSelected = outputListView.getSelectionModel().getSelectedItem();
        // DictionaryController.getSqLite().deleteRowDatabase("engvie", userSelected);
        DictionaryController.getSqLite().deleteRowDatabase("history", userSelected);
        DictionaryController.getSqLite().deleteRowDatabase("bookmark", userSelected);
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
                outputListView.getItems().add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        outputTextArea.setEditable(false);
        notFoundLable.setVisible(false);
        loadWordtoTrie();
        DictionaryController.getSqLite().createTableDatabase("history");
        DictionaryController.getSqLite().createTableDatabase("bookmark");
        inputTextField.textProperty().addListener(event -> {
            inputTextFieldEvent();
        });

        deleteTextFieldButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                inputTextField.clear();
            } 
        });

        outputListView.getSelectionModel().selectedItemProperty().addListener(event -> {
            outputListViewEvent();
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
