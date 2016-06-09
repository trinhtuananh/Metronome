/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trinh.metronome.audio;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author tuananh
 */
public class AudioPlayer {

    MediaPlayer mediaPlayer;
    public static final String MEDIA_PATH = "src/main/resources/sounds/beep.mp3";
    private int balance = 1;
    private long time = 0;

    public AudioPlayer() {
        File file = new File(MEDIA_PATH);
        String absolutePath = file.getAbsolutePath();
        Media media = new Media(Paths.get(absolutePath).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setBalance(-1);
    }

    public void play() {
        long tmp = System.currentTimeMillis();
        System.out.println(tmp - time);
        time = tmp;
        mediaPlayer.play();

    }

    public void stop()
    {
        if (mediaPlayer.isAutoPlay()) mediaPlayer.stop();
        
    }
    public void playLoop() {
        mediaPlayer.setAutoPlay(true);
//        IntStream.range(0, 10)
//                .forEach(i -> {
        mediaPlayer.setCycleCount(100);
        mediaPlayer.setOnRepeat(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.balance = balance * (-1);
            mediaPlayer.setBalance(balance);
        });
//                });
    }

}
