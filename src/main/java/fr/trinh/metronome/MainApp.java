package fr.trinh.metronome;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tuan anh TRINH
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Logger logger = Logger.getGlobal();
        logger.log(Level.SEVERE, "Welcome :D");
        logger.log(Level.SEVERE, this.getClass().getResource("/fxml/Scene.fxml").toString());
        Parent root = FXMLLoader.load(this.getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Metronome");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((e) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
