package view;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import controller.ControllerTest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView extends Application {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage mainStage) throws Exception {
        mainStage.setTitle("Collector");
        final Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        final Scene scene = new Scene(root);
        final Button btnPlay = (Button) scene.lookup("#btnPlay");
        final Button btnPause = (Button) scene.lookup("#btnPause");

        mainStage.setScene(scene);
        mainStage.show();
    }
}
