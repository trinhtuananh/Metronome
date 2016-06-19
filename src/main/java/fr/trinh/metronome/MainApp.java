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
