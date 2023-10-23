package org.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdditionController implements Initializable {
    @FXML
    private Button addWordsButton;

    @FXML
    private TextField englishTextField;

    @FXML
    private Label successNotification;

    @FXML
    private TextArea vietnameseTextArea;

    // public void addWordsButtonEvent() {
    //     addWordsButton.setOnAction(new EventHandler<ActionEvent>() {
    //         @Override
    //         public void handle(ActionEvent arg0) {
    //             // TODO Auto-generated method stub
    //         }
            
    //     });
    // }

    // public void englishTextFieldEvent() {
    //     englishTextField.textProperty().addListener(new ChangeListener<String>() {

    //         @Override
    //         public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
    //             // TODO Auto-generated method stub
    //             throw new UnsupportedOperationException("Unimplemented method 'changed'");
    //         }
            
    //     });
    // }

    // public void vietnameseTextAreaEvent() {
    //     vietnameseTextArea.textProperty().addListener(new ChangeListener<String>() {
    //         @Override
    //         public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
    //             // TODO Auto-generated method stub

    //         }
    //     });
    // }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Kiểm tra điều kiện gì đó thì mới cần thêm notification
        // addWordsButtonEvent();
        // englishTextFieldEvent();
        // vietnameseTextAreaEvent();
        

        successNotification.setVisible(false);

        addWordsButton.setOnAction(event -> {
            System.out.println("Add word test");
            successNotification.setVisible(true);
        });
    }
    
}
