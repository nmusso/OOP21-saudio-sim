package view;

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
import model.audiomanager.AudioManager;

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
        Button btnPlay = (Button) scene.lookup("#btnPlay");
        
        AudioManager.initContext();
        
        
        mainStage.setScene(scene);
        mainStage.show();
    }
}
