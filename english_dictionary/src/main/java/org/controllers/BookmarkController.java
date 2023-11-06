package org.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.features.*;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookmarkController implements Initializable {
    @FXML
    private JFXButton deleteTextFieldButton;

    @FXML
    private TextField inputTextField;

    @FXML
    private TableView<Word> bookmarkTable;

    @FXML
    private TableColumn<Word, String> descriptionColumn;

    @FXML
    private TableColumn<Word, String> pronunciationColumn;

    @FXML
    private TableColumn<Word, String> wordColumn;

    private static ObservableList<Word> bookmarkList() {
        ObservableList<Word> bookmark = FXCollections.observableArrayList();
        try {
            ResultSet bookmarkDatabase = DictionaryController.getSqLite().getTableDatabase("bookmark");
            while (bookmarkDatabase.next()) {
                String word_target = bookmarkDatabase.getString("word");
                String word_explain = bookmarkDatabase.getString("description");
                String word_pronunciation = bookmarkDatabase.getString("pronunciation");
                bookmark.add(new Word(word_target, word_pronunciation, word_explain));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookmark;
    };

    public void setCellValue() {
        wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        pronunciationColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_pronunciation"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookmarkTable.setItems(bookmarkList());
        setCellValue();    
    }

}
