package org.features;

import org.controllers.DictionaryController;

import javafx.concurrent.Task;
import javafx.scene.control.Label;

public class HandleAPIException {
    public static final String langException = "java.lang.Exception";
    public static final String unknownHostException = "java.net.UnknownHostException";

    public static void handleLangException(String textContent, String languagePronunciation, Label notificationLabel) {
        Task<Void> backgroundTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                VoiceRSS.voiceSpeak(textContent, languagePronunciation);
                DictionaryController.getMediaPlayer(VoiceRSS.audioPath).play();
                return null;
            }
        };
        backgroundTask.setOnFailed(event -> {
            String taskException = backgroundTask.getException().toString();
            if (taskException.substring(0, langException.length()).equals(langException)) {
                notificationLabel.setVisible(true);
            }
            System.out.println(backgroundTask.getException());
        });
        new Thread(backgroundTask).start();
    }

}
