package org.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.features.TranslateAPI;
import org.features.VoiceRSS;

import com.jfoenix.controls.JFXButton;
import com.voicerss.tts.Languages;

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
    private ImageView firstLanguageImage, secondLanguageImage;

    @FXML
    private Label firstLanguageLabel, secondLanguageLabel;

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
        if (firstLanguageTextArea.getText() != null || !firstLanguageTextArea.getText().equals("")) {
            try {
                secondLanguageTextArea.setText(TranslateAPI.translate(firstLanguageGoogleAPI, secondLanguageGoogleAPI, firstLanguageTextArea.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // if (secondLanguageTextArea.getText() != null || !secondLanguageTextArea.getText().equals("")) {
        //     try {
        //         firstLanguageTextArea.setText(TranslateAPI.translate(secondLanguageAbbreviation, firstLanguageAbbreviation, secondLanguageTextArea.getText()));
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }
    }

    public void soundFirstLanguageButtonEvent() {
        if (firstLanguageTextArea.getText() != null || !firstLanguageTextArea.getText().equals("")) {
            try {
                VoiceRSS.voiceSpeak(firstLanguageTextArea.getText(), firstLanguageVoiceRSS);
                DictionaryController.getMediaPlayer(VoiceRSS.audioPath).play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void soundSecondLanguageButtonEvent() {
        if (secondLanguageTextArea.getText() != null || !secondLanguageTextArea.getText().equals("")) {
            try {
                VoiceRSS.voiceSpeak(secondLanguageTextArea.getText(), secondLanguageVoiceRSS);
                DictionaryController.getMediaPlayer(VoiceRSS.audioPath).play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
