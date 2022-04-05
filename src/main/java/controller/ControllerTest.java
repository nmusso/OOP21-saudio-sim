package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.audiomanager.AudioManager;
import model.buffer.Buffer;
import model.buffer.BufferImpl;
import model.source.Source;
import model.source.SourceImpl;
import view.MultipleFxmlTest;

public class ControllerTest implements Initializable {
    @FXML private ComboBox<String> cmbTitleMusic;
    @FXML private BorderPane borderPaneTest;
    private Source s;

    public ControllerTest() {
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        AudioManager.initContext();

        final Buffer b = new BufferImpl("src/main/resources/InnoItalia.wav");
        s = new SourceImpl();

        try {
            s.generateSource(b.getID());
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 

        cmbTitleMusic.getItems().addAll("Prova 1", "Prova2");
    }

    @FXML public final void handlePlay(final Event event) {
        s.play();
    }

    @FXML public final void handlePause(final Event event) {
        s.pause();
    }
    
    @FXML public final void handleFinestra1(final Event event) {
        final MultipleFxmlTest test = new  MultipleFxmlTest();
        Pane view = test.getPage("finestra1.fxml");
        borderPaneTest.setCenter(view);
    }
    
    @FXML public final void handleFinestra2(final Event event) {
        final MultipleFxmlTest test = new  MultipleFxmlTest();
        Pane view = test.getPage("finestra2.fxml");
        borderPaneTest.setCenter(view);
    }
    
    
}
