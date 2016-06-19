/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trinh.metronome.audio;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Tuan anh TRINH
 */
public class AudioPlayer {


    private MediaPlayer mediaPlayer;
    public String mediaPath = "/sounds/beep.mp3";
    private StringProperty patternProperty = new SimpleStringProperty();
    private int cpt;
    private IntegerProperty rythme;

    public AudioPlayer(DoubleProperty r) {
        System.out.println(this.getClass().getResource(mediaPath).toString());
        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(mediaPath).toString()));
        mediaPlayer.setBalance(0);
        rythme = new SimpleIntegerProperty();
        rythme.bindBidirectional(r);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.setCycleCount(0);
        mediaPlayer.setAutoPlay(false);
        mediaPlayer.stop();

    }

    public void playLoop() {

        Double tab[] = parsePattern(patternProperty.get());
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setOnRepeat(() -> {
            try {
                double rythmeDouble = 60.0 / rythme.get() * 1000;
                Thread.sleep((long) rythmeDouble);
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

    public void onChange(ObservableValue<? extends MediaPlayer.Status> observable, MediaPlayer.Status oldValue, MediaPlayer.Status newValue) {
        if (newValue == MediaPlayer.Status.STOPPED) {
            mediaPlayer.stop();
        }
    }

    public Property<String> getPatternProperty() {
        return patternProperty;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    
    public void setMedia(Media media){
        mediaPlayer = new MediaPlayer(media);
    }

}
