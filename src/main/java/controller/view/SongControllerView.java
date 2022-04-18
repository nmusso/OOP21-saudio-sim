package controller.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import controller.MainController;
import controller.SongController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.buffer.Buffer;

/**
 * Controller of the view SongView.
 *
 */
public class SongControllerView implements Initializable, ControllerView {

    private static final String SONG_PATH = "/songs/";
    @FXML private Button btnImport;
    @FXML private Button btnPlay;
    @FXML private Button btnPause;
    @FXML private Button btnStop;
    @FXML private ComboBox<Buffer> cmbSongs;
    private SongController ctrl;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrl = ctrMain.getSongController();
        this.ctrl.setControllerView(this);
        try {
            addStartSongs();
        } catch (IOException e) {
            showError("Something went wrong during load buffer resources");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError(final String error) {
        final Alert alert = new Alert(AlertType.ERROR, error, ButtonType.OK);
        alert.show();
    }

    /**
     * Open a new FileChooser and import the selected items.
     * @param event  the event who triggered the method
     */
    @FXML
    public final void handlePress(final Event event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource file");
        fc.getExtensionFilters().add(new ExtensionFilter("wav", "*.wav"));

        final List<File> selected = fc.showOpenMultipleDialog(null);
        if (selected != null) {
            selected.forEach(file -> {
                ctrl.addBufferFromPath(file.getAbsolutePath());
            });

            updateComboBox();
        }
    }

    /**
     * Notify his app controller to play all the sources.
     * @param event  the event who triggered the method
     */
    @FXML
    public final void handlePlay(final Event event) {
        final int bufferID = cmbSongs.getSelectionModel().getSelectedItem().getID();
        ctrl.playSource(bufferID);

        btnImport.setDisable(true);
        btnPlay.setDisable(true);
        btnPause.setDisable(false);
        btnStop.setDisable(false);
        cmbSongs.setDisable(true);
    }

    /**
     * Notify his app controller to pause all the sources.
     * @param event  the event who triggered the method
     */
    @FXML
    public final void handlePause(final Event event) {
        ctrl.pauseSource();

        btnPlay.setDisable(false);
        btnPause.setDisable(true);
        btnStop.setDisable(false);
    }

    /**
     * Notify his app controller to stop all the sources.
     * @param event  the event who triggered the method
     */
    @FXML
    public final void handleStop(final Event event) {
        ctrl.stopSource();

        btnImport.setDisable(true);
        btnPlay.setDisable(false);
        btnPause.setDisable(true);
        btnStop.setDisable(true);
        cmbSongs.setDisable(false);
    }

    /**
     * Update the items of the combobox.
     */
    private void updateComboBox() {
        cmbSongs.getItems().clear();
        final var list = ctrl.getBufferList();
        Collections.sort(list, (b1, b2) -> Integer.compare(b2.getID(), b1.getID())); 
        cmbSongs.getItems().addAll(list);

        if (!cmbSongs.getItems().isEmpty()) {
            cmbSongs.getSelectionModel().select(0);
        }
    }

    /**
     * Import automatically all the wav in the resources path.
     * @throws IOException 
     */
    private void addStartSongs() throws IOException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(loader);
        Arrays.asList(resolver.getResources("classpath:songs/*.wav")).stream()
                .map(res -> res.getFilename())
                .forEach(res -> ctrl.addBufferFromResource(SONG_PATH + res));

        updateComboBox();
    }

    /**
     * Getter of the combobox with the buffers.
     * @return the combobox.
     */
    public ComboBox<Buffer> getCmbSongs() {
        return cmbSongs;
    }
}
