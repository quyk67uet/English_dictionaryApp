package org.controllers.dictionary;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controllers.MainController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class AdditionController implements Initializable {
    @FXML
    private HTMLEditor englishEditor, vietnameseEditor;

    @FXML
    private Button addWordsButton;

    @FXML
    private Label successNotification, failureNotification;

    @FXML
    private ImageView loadingImage;

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

    private String getPlainText(String htmlText) {
        WebView webView = new WebView();
        webView.getEngine().loadContent(htmlText);
        return (String) webView.getEngine().executeScript("document.body.innerText");
    }

    public void addWordsButtonEvent() {
        // TODO: Alert when one or two editor have no texts
        failureNotification.setVisible(false);
        successNotification.setVisible(false);
        if (getPlainText(englishEditor) != null && !getPlainText(englishEditor).equals("") && !getPlainText(englishEditor).trim().isEmpty() && getPlainText(vietnameseEditor) != null && !getPlainText(vietnameseEditor).equals("") && !getPlainText(vietnameseEditor).trim().isEmpty()) {
            loadingImage.setVisible(true);
            MainController.getSqLite().insertWordDatabase(getPlainText(englishEditor), vietnameseEditor.getHtmlText(), getPlainText(vietnameseEditor));
            if (!MainController.getTrie().searchWord(getPlainText(englishEditor))) {
                MainController.getTrie().insert(getPlainText(englishEditor));
            }
            System.out.println("Word: " + getPlainText(englishEditor.getHtmlText()));
            System.out.println(vietnameseEditor.getHtmlText());
            System.out.println("Vie: " + getPlainText(vietnameseEditor.getHtmlText()));
            loadingImage.setVisible(false);
            successNotification.setVisible(true);
        }
        else {
            failureNotification.setVisible(true);
        }
        System.out.println(englishEditor.getHtmlText());
        System.out.println("Test:" + getPlainText(englishEditor) + ".");
        System.out.println(getPlainText(vietnameseEditor.getHtmlText()));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Kiểm tra điều kiện gì đó thì mới cần thêm notification
        // addWordsButtonEvent();
        // englishTextFieldEvent();
        // vietnameseTextAreaEvent();
        loadingImage.setVisible(false);
        failureNotification.setVisible(false);
        successNotification.setVisible(false);

        englishEditor.setOnKeyPressed(event -> {
            failureNotification.setVisible(false);
            successNotification.setVisible(false);
        });

        vietnameseEditor.setOnKeyPressed(event -> {
            failureNotification.setVisible(false);
            successNotification.setVisible(false);
        });
        
        addWordsButton.setOnAction(event -> {
            addWordsButtonEvent();
        });
    }

}