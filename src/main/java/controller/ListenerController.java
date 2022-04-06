package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ListenerController implements Initializable {
    @FXML private Button btn;
    @FXML private TabPane listenerPane;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }
    
    @FXML public final void handleAddPlugin(final Event event) {
        System.out.println(event.getSource());
    }

}
