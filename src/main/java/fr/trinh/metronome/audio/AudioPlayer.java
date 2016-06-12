/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trinh.metronome.audio;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Tuan anh TRINH
 */
public class AudioPlayer {

    public final static char LEFT = 'L';
    public final static char RIGTH = 'R';
    public final static char MIDDLE = 'M';

    MediaPlayer mediaPlayer;
    public static final String MEDIA_PATH = "/sounds/beep.mp3";
    private StringProperty patternProperty = new SimpleStringProperty();
    private int cpt;

    private AudioPlayer() {
        Media media = new Media(this.getClass().getResource(MEDIA_PATH).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setBalance(0);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        if (mediaPlayer.isAutoPlay()) {
            mediaPlayer.stop();
        }

    }

    public void playLoop() {

        Double tab[] = parsePattern(patternProperty.get());
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setOnRepeat(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (cpt == tab.length) {
                cpt = 0;
            }
            mediaPlayer.setBalance(tab[cpt]);
            cpt++;
        });
    }

    public Double[] parsePattern(String pattern) throws IllegalArgumentException, NumberFormatException {
        String[] stringArray = pattern.split(";");
        Double[] doubleArray = new Double[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            String s = stringArray[i];
            double nb = Double.parseDouble(s.trim());
            if (nb > 1 || nb < (-1)) {
                throw new IllegalArgumentException("Invalid pattern : \"" + pattern + "\"");
            }
            doubleArray[i] = nb;
        }
        return doubleArray;
    }

    public static AudioPlayer getAudioPlayer() {
        return new AudioPlayer();
    }

    public Property<String> getPatternProperty() {
        return patternProperty;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}
