/**
*MetronomeFX is a java application that allow the user to play a sound and set *the balance
*    Copyright (C) 2016  Tuan anh TRINH
*
*    This program is free software: you can redistribute it and/or *modify
*    it under the terms of the GNU General Public License as published *by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/*licenses/
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
