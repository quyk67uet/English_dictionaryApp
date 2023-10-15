package org.controllers;

import org.features.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SearchController implements Initializable {
    @FXML
    private Button deleteButton;

    @FXML
    private Button edit_button;

    @FXML
    private Button volumn;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputField;

    private DictionaryCommandline dictionaryCmd;
    private List<String> searchEnglishResult;
    private List<String> searchVietnameseResult;
    private List<String> historyResult;
    private static final String exportDirectory = "english_dictionary/src/main/resources/assets/ExportDictionary.txt";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        dictionaryCmd = new DictionaryCommandline();
        searchEnglishResult = new ArrayList<>();
        searchVietnameseResult = new ArrayList<>();
        historyResult = new ArrayList<>();
        inputField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                if (oldValue == null || oldValue.equals("")) {
                    searchEnglishResult.clear();
                    outputListView.getItems().clear();
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
                if (newValue != null && !newValue.equals("")){
                    searchEnglishResult.clear();
                    outputListView.getItems().clear();
                    for (String x : dictionaryCmd.getMapWord().keySet()) {
                        if (x.substring(0, newValue.length()).equals(newValue)) {
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
                }
            }
        });

        outputListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    searchEnglishResult.clear();
                    searchVietnameseResult.clear();
                    String userSelected = outputListView.getSelectionModel().getSelectedItem();
                    dictionaryCmd.addFileWord(userSelected, "", exportDirectory);
                    historyResult.add(userSelected);
                    for (int i = 0; i < dictionaryCmd.getMapWord().get(userSelected).size(); i++) {
                        searchVietnameseResult.add(dictionaryCmd.getMapWord().get(userSelected).get(i));
                    }
                    Collections.sort(searchVietnameseResult, new Comparator<String>() {
                        @Override
                        public int compare(String a, String b) {
                            return a.compareTo(b);
                        }
                    });
                    outputField.clear();
                    for (int i = 0; i < searchVietnameseResult.size(); i++) {
                        outputField.appendText("Nghĩa của từ là: " + searchVietnameseResult.get(i));
                    }
                }
            }
        });
    }

}
