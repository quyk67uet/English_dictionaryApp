package org.controllers;

import org.features.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class SearchController implements Initializable {
    @FXML
    private Button deleteTextFieldButton, editButton, soundButton, deleteWordFromDatabaseButton;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private Label notFoundLable;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        dictionaryCmd = new DictionaryCommandline();
        searchEnglishResult = new ArrayList<>();
        searchVietnameseResult = new ArrayList<>();
        historyResult = new ArrayList<>();
        notFoundLable.setVisible(false);

        if (inputTextField.getText() == null || inputTextField.getText().trim().isEmpty()) {
            try (BufferedReader buffer_reader = new BufferedReader(new FileReader(exportDirectory))) {
                while (buffer_reader.ready())
                {
                    String word_pair = buffer_reader.readLine();
                    String[] word_from_pair = word_pair.split("\t");
                    if (word_from_pair.length < 1) {
                        System.out.println("File format error");
                    }
                    else 
                    {
                        if (historyResult.contains(word_from_pair[0]))  {
                            historyResult.remove(word_from_pair[0]);
                        }
                        historyResult.add(word_from_pair[0]);
                    }
                }
            } catch (IOException e) {
                System.out.println("Having problem with reading from files");
            }
            Collections.reverse(historyResult);
            outputListView.getItems().addAll(historyResult);
            Collections.reverse(historyResult);
        }
        inputTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchEnglishResult.clear();
                outputListView.getItems().clear();
                outputTextArea.clear();
                notFoundLable.setVisible(false);

                if (inputTextField.getText() == null || inputTextField.getText().trim().isEmpty()) {
                    try (BufferedReader buffer_reader = new BufferedReader(new FileReader(exportDirectory))) {
                        while (buffer_reader.ready())
                        {
                            String word_pair = buffer_reader.readLine();
                            String[] word_from_pair = word_pair.split("\t");
                            if (word_from_pair.length < 1) {
                                System.out.println("File format error");
                            }
                            else 
                            {
                                if (historyResult.contains(word_from_pair[0]))  {
                                    historyResult.remove(word_from_pair[0]);
                                }
                                historyResult.add(word_from_pair[0]);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Having problem with reading from files");
                    }
                    Collections.reverse(historyResult);
                    outputListView.getItems().addAll(historyResult);
                    Collections.reverse(historyResult);
                }

                else if (newValue != null && !newValue.equals("")){
                    for (String x : dictionaryCmd.getMapWord().keySet()) {
                        if (newValue.length() <= x.length() && x.substring(0, newValue.length()).equals(newValue)) {
                            searchEnglishResult.add(x);
                        }
                    }
                    Collections.sort(searchEnglishResult, new Comparator<String>() {
                        @Override
                        public int compare(String a, String b) {
                            return a.compareTo(b);
                        }
                        
                    });
                    outputListView.getItems().addAll(searchEnglishResult);
                    // Design thêm dòng bên dưới thanh search nếu không tìm được từ vừa nhập
                    if (outputListView.getItems().size() == 0) {
                        notFoundLable.setVisible(true);
                    }
                }
            }
        });

        deleteTextFieldButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                inputTextField.clear();
            }
            
        });

        outputListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (outputListView.getItems().size() != 0) {
                    searchEnglishResult.clear();
                    searchVietnameseResult.clear();
                    String userSelected = outputListView.getSelectionModel().getSelectedItem();
                    System.out.println("Honh:" + userSelected + "biet");
                    try {
                        if(historyResult.contains(userSelected)) {
                            historyResult.remove(userSelected); 
                        }
                        else {
                            
                        }
                        dictionaryCmd.addFileWord(userSelected, "", exportDirectory);
                        writeToFile(exportDirectory, userSelected);
                        historyResult.add(userSelected);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < dictionaryCmd.getMapWord().get(userSelected).size(); i++) {
                        searchVietnameseResult.add(dictionaryCmd.getMapWord().get(userSelected).get(i));
                    }
                    Collections.sort(searchVietnameseResult, new Comparator<String>() {
                        @Override
                        public int compare(String a, String b) {
                            return a.compareTo(b);
                        }
                    });
                    outputTextArea.clear();
                    for (int i = 0; i < searchVietnameseResult.size(); i++) {
                        outputTextArea.appendText("Nghĩa của từ là: " + searchVietnameseResult.get(i));
                    }
                }
            }
        });
    }

}
