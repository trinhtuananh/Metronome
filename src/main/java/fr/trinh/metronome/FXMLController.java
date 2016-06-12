package fr.trinh.metronome;

import fr.trinh.metronome.audio.AudioPlayer;
import fr.trinh.metronome.audio.ExceptionDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Tuan anh TRINH
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField patternTextField;

    @FXML
    ToggleButton toggleButton;

    @FXML
    Slider volumeSlider;

    private AudioPlayer audioPlayer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        audioPlayer = AudioPlayer.getAudioPlayer();
        toggleButton.selectedProperty().addListener((obs, o, n) -> {
            if (n) {
                play();
            } else {
                stop();
            }
        });
        stop();
        audioPlayer.getMediaPlayer().volumeProperty().bindBidirectional(volumeSlider.valueProperty());
        patternTextField.textProperty().bindBidirectional(audioPlayer.getPatternProperty());
    }

    public void play() {
        try {
            audioPlayer.playLoop();
            this.patternTextField.setEditable(false);
            toggleButton.setText("Stop...");
        } catch (Exception e) {
            new ExceptionDialog(e);
        }
    }

    public void stop() {
        audioPlayer.stop();
        this.patternTextField.setEditable(true);
        toggleButton.setText("Play...");
    }

}
