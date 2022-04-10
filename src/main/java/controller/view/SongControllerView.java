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

    private static final String SEP = System.getProperty("file.separator");
    private final BufferFactory factory = new BufferFactoryImpl();
    @FXML private Button btnImport;
    @FXML private Button btnPlay;
    @FXML private Button btnPause;
    @FXML private Button btnStop;
    @FXML private ComboBox<String> cmbSongs;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final String folderPath = "src" + SEP + "main" + SEP + "resources" + SEP + "songs" + SEP;
        final File folder = new File(folderPath);

        for (final File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                createBuffer(file);
            } 
        }

        updateComboBox();
    }

    @Override
    public void setControllerApplication(final MainController ctrMain) {
        // TODO Auto-generated method stub

    }

    @FXML
    public final void handlePress(final Event event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource file");
        fc.getExtensionFilters().add(new ExtensionFilter("wav", "*.wav"));

        final List<File> selected = fc.showOpenMultipleDialog(null);
        if (selected != null) {
            selected.forEach(file -> {
                createBuffer(file);
            });

            updateComboBox();
        }
    }

    @FXML
    public final void handlePlay(final Event event) {
        final String id = Character.toString(cmbSongs.getSelectionModel().getSelectedItem().charAt(0));
        final int bufferID = Integer.parseInt(id);
        final Buffer buf = BufferCache.INSTANCE.getBufferFromID(bufferID);
        // TODO questo è il buffer che serve per le source, già testato il corretto funzionamento
    }

    @FXML
    public final void handlePause(final Event event) {
    }

    @FXML
    public final void handleStop(final Event event) {
    }

    private void createBuffer(final File file) {
        try {
            factory.createBufferFromPath(file.getAbsolutePath());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateComboBox() {
        final var cache = BufferCache.INSTANCE.getCacheMap();
        cmbSongs.getItems().clear();
        cache.forEach((path, buffer) -> cmbSongs.getItems().add(buffer.toString()));

        if (!cmbSongs.getItems().isEmpty()) {
            cmbSongs.getSelectionModel().select(0);
        }
    }
}
