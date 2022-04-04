package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.audiomanager.AudioManager;
import model.buffer.Buffer;
import model.buffer.BufferImpl;
import model.source.Source;
import model.source.SourceImpl;

public class ControllerTest implements Initializable {
    @FXML private ComboBox<String> cmbTitleMusic;
    private Source s;

    public ControllerTest() {
    }

    /**
     * Init.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        AudioManager.initContext();

        final Buffer b = new BufferImpl("src/main/resources/InnoItalia.wav");
        s = new SourceImpl();

        try {
            s.generateSource(b.generateBuffer());
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
}
