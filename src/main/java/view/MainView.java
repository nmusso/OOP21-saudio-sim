package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        final Parent root = FXMLLoader.load(getClass().getResource("provafxml.fxml"));
        final Scene scene = new Scene(root);
        final Button btnsource = (Button) scene.lookup("#addSource");
        btnsource.setOnAction((ActionEvent event) -> {
            System.out.println("add Source");
        });
        // set transparent
        scene.setFill(Color.TRANSPARENT);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
