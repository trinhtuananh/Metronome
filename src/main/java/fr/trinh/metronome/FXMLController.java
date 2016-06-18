package fr.trinh.metronome;

import fr.trinh.metronome.audio.AudioPlayer;
import fr.trinh.metronome.audio.ExceptionDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

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

    @FXML
    Slider tempoSlider;
    
    @FXML
    Label tempoLabel;


    private AudioPlayer audioPlayer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        toggleButton.selectedProperty().addListener((obs, o, n) -> {
            if (n) {
                play();
            } else {
                stop();
            }
        });
        audioPlayer = new AudioPlayer(tempoSlider.valueProperty());
        stop();
        audioPlayer.getMediaPlayer().volumeProperty().bindBidirectional(volumeSlider.valueProperty());
        patternTextField.textProperty().bindBidirectional(audioPlayer.getPatternProperty());
        StringConverter<Number> converter = new NumberStringConverter();
        tempoSlider.valueProperty().addListener(this::onChange);
        Bindings.bindBidirectional(tempoLabel.textProperty(), tempoSlider.valueProperty(), converter);

    }

    public void onChange(ObservableValue t, Number old, Number newValue){
        double value = Math.round(newValue.doubleValue());
        tempoSlider.valueProperty().set(value);
    }
    public void play() {
        try {
            audioPlayer.playLoop();
            this.patternTextField.setEditable(false);
            toggleButton.setText("Stop...");
        } catch (Exception e) {
            e.printStackTrace();
            new ExceptionDialog(e);
        }
    }

    public void stop() {
        audioPlayer.stop();
        this.patternTextField.setEditable(true);
        toggleButton.setText("Play...");
    }

}
