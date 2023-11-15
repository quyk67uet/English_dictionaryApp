package org.controllers.dictionary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.features.dictionary.*;
import org.controllers.MainController;
import com.jfoenix.controls.JFXButton;
import com.voicerss.tts.Languages;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TranslationController implements Initializable {

    @FXML
    private TextArea firstLanguageTextArea, secondLanguageTextArea;

    @FXML
    private ImageView firstLanguageImage, secondLanguageImage, loadingImage;

    @FXML
    private Label firstLanguageLabel, secondLanguageLabel, noWordsLabel, noInternetLabel;

    @FXML
    private JFXButton switchLanguageButton, translateButton, soundFirstLanguageButton, soundSecondLanguageButton;

    private static String firstLanguageGoogleAPI = "en";
    private static String secondLanguageGoogleAPI = "vi";
    private static String firstLanguageVoiceRSS = Languages.English_GreatBritain;
    private static String secondLanguageVoiceRSS = Languages.Vietnamese;

    
    public void switchLanguageButtonEvent() {
        // TextArea switchTextArea = firstLanguageTextArea;
        // firstLanguageTextArea = secondLanguageTextArea;
        // secondLanguageTextArea = switchTextArea;
        
        String switchText = firstLanguageTextArea.getText();
        firstLanguageTextArea.setText(secondLanguageTextArea.getText());
        secondLanguageTextArea.setText(switchText);

        String switchGoogleAPI = firstLanguageGoogleAPI;
        firstLanguageGoogleAPI = secondLanguageGoogleAPI;
        secondLanguageGoogleAPI = switchGoogleAPI;

        String switchVoiceRSS = firstLanguageVoiceRSS;
        firstLanguageVoiceRSS = secondLanguageVoiceRSS;
        secondLanguageVoiceRSS = switchVoiceRSS;

        Image switchImage = firstLanguageImage.getImage();
        firstLanguageImage.setImage(secondLanguageImage.getImage());
        secondLanguageImage.setImage(switchImage);
 
        String switchLabelText = firstLanguageLabel.getText();
        firstLanguageLabel.setText(secondLanguageLabel.getText());
        secondLanguageLabel.setText(switchLabelText);
    }

    public void translateButtonEvent() {
        noWordsLabel.setVisible(false);
        noInternetLabel.setVisible(false);
        if (firstLanguageTextArea.getText() != null && !firstLanguageTextArea.getText().equals("")) {
            Task<String> translateTask = new Task<String>() {
            @Override
            protected String call() throws Exception {
                loadingImage.setVisible(true);
                return TranslateAPI.translate(firstLanguageGoogleAPI, secondLanguageGoogleAPI, firstLanguageTextArea.getText());
            }
            };
            translateTask.setOnFailed(event -> {
                loadingImage.setVisible(false);
                String translateException = translateTask.getException().toString();
                System.out.println(translateException);
                if (translateException.substring(0, MainController.langException.length()).equals(MainController.langException)) {
                    noWordsLabel.setVisible(true);
                    System.out.println("no words selected");    
                } else if (translateException.substring(0, MainController.unknownHostException.length()).equals(MainController.unknownHostException)) {
                    noInternetLabel.setVisible(true);
                    System.out.println("No internet connection");
                }
            });
            translateTask.setOnSucceeded(event -> {
                loadingImage.setVisible(false);
                try {
                    secondLanguageTextArea.setText(translateTask.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            new Thread(translateTask).start();
        } else {
            noWordsLabel.setVisible(true);
        }
    }

    public void soundFirstLanguageButtonEvent() {
        // TODO: Add lable about notification
        loadingImage.setVisible(false);
        noWordsLabel.setVisible(false);
        noInternetLabel.setVisible(false);
        try {
            Task<Void> soundFirstLanguage = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    loadingImage.setVisible(true);
                    VoiceRSS.voiceSpeak(firstLanguageTextArea.getText(), firstLanguageVoiceRSS);
                    MainController.getMediaPlayer(VoiceRSS.audioPath).play();
                    return null;
                }
            };
            soundFirstLanguage.setOnFailed(event -> {
                loadingImage.setVisible(false);
                String taskException = soundFirstLanguage.getException().toString();
                if (taskException.substring(0, MainController.langException.length()).equals(MainController.langException)) {
                    noWordsLabel.setVisible(true);
                    System.out.println("no words selected");    
                } else if (taskException.substring(0, MainController.unknownHostException.length()).equals(MainController.unknownHostException)) {
                    noInternetLabel.setVisible(true);
                    System.out.println("No internet connection");
                }
            });
            soundFirstLanguage.setOnSucceeded(event -> {
                loadingImage.setVisible(false);
            });
            new Thread(soundFirstLanguage).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void soundSecondLanguageButtonEvent() {
        noWordsLabel.setVisible(false);
        noInternetLabel.setVisible(false);
        try {
            Task<Void> soundSecondLanguage = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    loadingImage.setVisible(true);
                    VoiceRSS.voiceSpeak(secondLanguageTextArea.getText(), secondLanguageVoiceRSS);
                    MainController.getMediaPlayer(VoiceRSS.audioPath).play();
                    return null;
                }
            };
            soundSecondLanguage.setOnFailed(event -> {
                loadingImage.setVisible(false);
                String taskException = soundSecondLanguage.getException().toString();
                if (taskException.substring(0, MainController.langException.length()).equals(MainController.langException)) {
                    noWordsLabel.setVisible(true);
                    System.out.println("no words selected");    
                } else if (taskException.substring(0, MainController.unknownHostException.length()).equals(MainController.unknownHostException)) {
                    noInternetLabel.setVisible(true);
                    System.out.println("No internet connection");
                }
            });
            soundSecondLanguage.setOnSucceeded(event -> {
                loadingImage.setVisible(false);
            });
            new Thread(soundSecondLanguage).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstLanguageTextArea.setStyle("-fx-font-size: 18px;");
        secondLanguageTextArea.setStyle("-fx-font-size: 18px;");
        secondLanguageTextArea.setEditable(false);
        loadingImage.setVisible(false);
        noWordsLabel.setVisible(false);
        noInternetLabel.setVisible(false);
        // firstLanguageTextArea.textProperty().addListener(event -> {
        //     secondLanguageTextArea.clear();
        // });

        switchLanguageButton.setOnAction(event -> {
            switchLanguageButtonEvent();
        });

        translateButton.setOnAction(event -> {
            translateButtonEvent();
        });

        soundFirstLanguageButton.setOnAction(event -> {
            soundFirstLanguageButtonEvent();
        });
        
        soundSecondLanguageButton.setOnAction(event -> {
            soundSecondLanguageButtonEvent();
        });
    }
}
