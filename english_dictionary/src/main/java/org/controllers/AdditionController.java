package org.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class AdditionController implements Initializable {
    @FXML
    private HTMLEditor englishEditor, vietnameseEditor;
    
    @FXML
    private Button addWordsButton;

    @FXML
    private Label successNotification;

    public String getPlainText(HTMLEditor htmlEditor) {
        // WebView webView = new WebView();
        // webView.getEngine().loadContent(htmlEditor.getHtmlText());
        // Worker<Void> worker = webView.getEngine().getLoadWorker();
        // StringBuilder stringBuilder = new StringBuilder();
        // worker.stateProperty().addListener((observable, oldValue, newValue) -> {
        //     if (newValue == Worker.State.SUCCEEDED) {
        //         String script = "document.body.innerText";
        //         String plainText = webView.getEngine().executeScript(script).toString();
        //         System.out.println("Plain Text: " + plainText);
        //         stringBuilder.setLength(0);
        //         stringBuilder.append(plainText);
        //     }
        // });

        // return stringBuilder.toString();
        
        String result = "";

        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlEditor.getHtmlText());
        final StringBuffer text = new StringBuffer(htmlEditor.getHtmlText().length());

        while (matcher.find()) {
            matcher.appendReplacement(text, " ");
        }
        matcher.appendTail(text);
        result = text.toString().trim();  
        return result;
    }

    public void addWordsButtonEvent() {
        // TODO: Alert when one or two editor have no texts
        // if (englishEditor.getHtmlText() != null && !englishEditor.getHtmlText().equals("")) {
        //     if (vietnameseEditor.getHtmlText() != null && !vietnameseEditor.getHtmlText().equals(""))  {
                DictionaryController.getSqLite().insertWordDatabase(getPlainText(englishEditor), vietnameseEditor.getHtmlText(), getPlainText(vietnameseEditor));
                if (!DictionaryController.getTrie().searchWord(getPlainText(englishEditor))) {
                    DictionaryController.getTrie().insert(getPlainText(englishEditor));
                }

            
        
        System.out.println(getPlainText(englishEditor));
        System.out.println(vietnameseEditor.getHtmlText());
        System.out.println(getPlainText(vietnameseEditor));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Kiểm tra điều kiện gì đó thì mới cần thêm notification
        // addWordsButtonEvent();
        // englishTextFieldEvent();
        // vietnameseTextAreaEvent();
        

        successNotification.setVisible(false);

        addWordsButton.setOnAction(event -> {
            
            System.out.println("Add word test");
            addWordsButtonEvent();
            successNotification.setVisible(true);
        });
    }
    
}
