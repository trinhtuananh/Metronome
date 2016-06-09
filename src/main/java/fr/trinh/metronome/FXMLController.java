package fr.trinh.metronome;

import fr.trinh.metronome.audio.AudioPlayer;
import java.applet.Applet;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        onClick();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void onClick()
    {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.playLoop();
//        for (int i = 0; i!= 5 ; i++)
//        {
//            
//            try {
//        audioPlayer.play();
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            System.out.println(System.currentTimeMillis());
//            
//        }
    }
}
