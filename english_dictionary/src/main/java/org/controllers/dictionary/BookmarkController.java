package org.controllers.dictionary;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controllers.MainController;
import org.features.dictionary.*;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class BookmarkController implements Initializable {
    @FXML
    private JFXButton deleteTextFieldButton, deleteRowTableButton;

    @FXML
    private ImageView loadingImage;

    @FXML
    private TextField inputTextField;

    @FXML
    private Label noWordsLabel, deleteLabel;

    @FXML
    private TableView<Word> bookmarkTable;

    @FXML
    private TableColumn<Word, String> descriptionColumn;

    @FXML
    private TableColumn<Word, String> pronunciationColumn;

    @FXML
    private TableColumn<Word, String> wordColumn;

    private ObservableList<Word> bookmarkList = getBookmarkList();

    private ObservableList<Word> getBookmarkList() {
        ObservableList<Word> bookmark = FXCollections.observableArrayList();
        Task<Void> bookmarkList = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ResultSet bookmarkDatabase = MainController.getSqLite().getTableDatabase("bookmark");
                while (bookmarkDatabase.next()) {
                    String word_target = bookmarkDatabase.getString("word");
                    String word_explain = bookmarkDatabase.getString("description");
                    String word_pronunciation = bookmarkDatabase.getString("pronunciation");
                    bookmark.add(new Word(word_target, word_pronunciation, word_explain));
                }
                return null;
            }
        };
        new Thread(bookmarkList).start();
        return bookmark;
    };

    public void setCellValue() {
        wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        pronunciationColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_pronunciation"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));

        descriptionColumn.setCellFactory(new Callback<TableColumn<Word, String>, TableCell<Word, String>>() {
            @Override
            public TableCell<Word, String> call(TableColumn<Word, String> column) {
                TableCell<Word, String> cell = new TableCell<>();
                Text text = new Text();
                cell.setGraphic(text);
                cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
                text.wrappingWidthProperty().bind(cell.widthProperty());
                text.textProperty().bind(cell.itemProperty());
                return cell ;
            }
        });
    }

    public void inputTextFieldEvent() {
        noWordsLabel.setVisible(false);
        deleteLabel.setVisible(false);
        bookmarkTable.setItems(FXCollections.emptyObservableList());
        if (inputTextField.getText() == null || inputTextField.getText().trim().isEmpty()) {
            bookmarkTable.setItems(bookmarkList);
        }
        else {
            String inputText = inputTextField.getText();
            ObservableList<Word> textFieldPrefix = FXCollections.observableArrayList();
            if (inputTextField.getText() != null) {
                for (Word word : bookmarkList) {
                    if (word.getWord_target().length() >= inputText.length()) {
                        if (word.getWord_target().substring(0, inputText.length()).equals(inputText)) {
                            textFieldPrefix.add(word);
                        }
                    }
                }
            }
            bookmarkTable.setItems(textFieldPrefix);
        }
        // bookmarkTable.setItems(bookmarkList);
    }

    public void deleteRowTableButtonEvent() {
        noWordsLabel.setVisible(false);
        deleteLabel.setVisible(false);
        Word rowSelected = bookmarkTable.getSelectionModel().getSelectedItem();
        if (rowSelected != null) {
            Alert rowBookmark = MainController.getAlertManagement().showAlert(Alert.AlertType.CONFIRMATION, "The selected rows will be deleted! Action cannot be undone! ", "Do you want to continue ?");
            if (rowBookmark.showAndWait().get().getButtonData() == ButtonBar.ButtonData.YES) {
                Task<Void> deleteRow = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        loadingImage.setVisible(true);
                        MainController.getSqLite().deleteRowDatabase("bookmark", rowSelected.getWord_target());
                        bookmarkList.remove(bookmarkTable.getSelectionModel().getSelectedItem());
                        bookmarkTable.setItems(bookmarkList);
                        return null;
                    }
                };
                deleteRow.setOnFailed(event -> {
                    loadingImage.setVisible(false);
                    System.out.println(deleteRow.getException().getMessage());
                });
                deleteRow.setOnSucceeded(event -> {
                    loadingImage.setVisible(false);
                    deleteLabel.setVisible(true);
                });
                new Thread(deleteRow).start();
            }
        } else {
            noWordsLabel.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadingImage.setVisible(false);
        noWordsLabel.setVisible(false);
        deleteLabel.setVisible(false);
        setCellValue();
        bookmarkTable.setItems(bookmarkList);
    
        deleteTextFieldButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (!inputTextField.getText().equals("")) inputTextField.clear();
            }
        });

        inputTextField.textProperty().addListener(event -> {
            inputTextFieldEvent();
        });

        deleteRowTableButton.setOnAction(event -> {
            deleteRowTableButtonEvent();
        });
    }

}