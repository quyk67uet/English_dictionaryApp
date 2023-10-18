package org.controllers;

import java.net.URL;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class TranslationController implements Initializable {
    @FXML
    private Button changeLanguageButton, translateButton;

    @FXML
    private TextArea englishTextArea, vietnameseTextArea;

    // @FXML
    // private void changeLanguageButtonEvent() {
    //     changeLanguageButton.setOnAction(new EventHandler<ActionEvent>() {
    //         @Override
    //         public void handle(ActionEvent arg0) {
    //             // TODO Auto-generated method stub
    //         }
            
    //     });
    // }

    @FXML
    private void translateButtonEvent() {
        translateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
            }
            
        });
    }

    @FXML
    public void englishTextAreaEvent() {
        englishTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                // TODO Auto-generated method stub

            }
            
        });
    }

    @FXML
    public void vietnameseTextAreaEvent() {
        vietnameseTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                // TODO Auto-generated method stub

            }
            
        });
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        changeLanguageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
            }
            
        });
        // changeLanguageButtonEvent();
        // translateButtonEvent();
        // englishTextAreaEvent();
        // vietnameseTextAreaEvent();
    }
    
    
}
