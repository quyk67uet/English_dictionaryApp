package org.features.dictionary;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManagement {
    private Media media;
    private MediaPlayer mediaPlayer;

    public void playMedia(String path) {
        media = new Media(getClass().getResource(path).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        // mediaPlayer.pause();
    }
    
    public void pauseMedia(String path) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    
}
