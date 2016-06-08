/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trinh.metronome.audio;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author tuananh
 */
public class AudioPlayer {

    MediaPlayer mediaPlayer;
    public static final String MEDIA_PATH = "src/main/resources/sounds/bip.mp3";

    public AudioPlayer() {
        File file = new File(MEDIA_PATH);
        String absolutePath = file.getAbsolutePath();
        Media media = new Media(Paths.get(absolutePath).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setBalance(-1);
    }

    public void play() {
        mediaPlayer.play();
    }
    
    public void playLoop(){
                    mediaPlayer.setAutoPlay(true);
        IntStream.range(0, 10)
                .forEach(i -> {
                    play();
                   mediaPlayer.setCycleCount(10);
                    System.out.println(mediaPlayer.seton
                    mediaPlayer.setBalance(1);
                        });
    }

}
