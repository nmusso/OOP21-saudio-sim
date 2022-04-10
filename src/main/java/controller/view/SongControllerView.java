package controller.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.sound.sampled.UnsupportedAudioFileException;

import controller.MainController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.buffer.Buffer;
import model.buffer.BufferCache;
import model.buffer.BufferFactory;
import model.buffer.BufferFactoryImpl;

public class SongControllerView implements Initializable, ControllerView {

    @FXML private Button btnImport;
    @FXML private Button btnPlay;
    @FXML private Button btnPause;
    @FXML private Button btnStop;
    @FXML private ComboBox<String> cmbSongs;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @Override
    public void setControllerApplication(final MainController ctrMain) {
        // TODO Auto-generated method stub

    }

    @FXML
    public final void handlePress(final Event event) {
        final BufferFactory bf = new BufferFactoryImpl();
        final FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource file");
        fc.getExtensionFilters().add(new ExtensionFilter("wav", "*.wav"));

        final List<File> selected = fc.showOpenMultipleDialog(null);
        if (selected != null) {
            selected.forEach(file -> {
                try {
                    bf.createBufferFromPath(file.getAbsolutePath());
                } catch (UnsupportedAudioFileException | IOException e) {
                    e.printStackTrace();
                }
            });

            final var cache = BufferCache.INSTANCE.getCacheMap();
            cmbSongs.getItems().clear();
            cache.forEach((path, buffer) -> cmbSongs.getItems().add(buffer.toString()));
        }
    }
}
