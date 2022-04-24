package view.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import controller.MainController;
import controller.SongController;
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
import view.SongView;

/**
 * Controller of the view SongView.
 *
 */
public class SongControllerView implements Initializable, ControllerView, SongView {

    @FXML
    private Button btnImport;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;
    @FXML
    private ComboBox<Buffer> cmbSongs;
    private SongController ctrl;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrl = ctrMain.getSongController();
        this.ctrl.setControllerView(this);
        try {
            ctrl.addStartSongs();
        } catch (IOException e) {
            showMessage("Something went wrong during load buffer resources");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComboBox<Buffer> getCmbSongs() {
        return cmbSongs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String error) {
        final Alert alert = new Alert(AlertType.ERROR, error, ButtonType.OK);
        alert.show();
    }

    /**
     * Open a new FileChooser and import the selected items.
     * 
     * @param event the event who triggered the method
     */
    @FXML
    private void handlePress() { // NOPMD: Called by JavaFX
        final FileChooser fc = new FileChooser();
        fc.setTitle("Open file");
        fc.getExtensionFilters().add(new ExtensionFilter("wav", "*.wav"));

        final List<File> selected = fc.showOpenMultipleDialog(null);
        if (selected != null) {
            selected.stream()
                    .map(file -> file.getAbsolutePath())
                    .forEach(ctrl::addBufferFromPath);
        }
    }

    /**
     * Notify his app controller to play all the sources.
     * 
     * @param event the event who triggered the method
     */
    @FXML
    private void handlePlay() { // NOPMD: Called by JavaFX
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
     * 
     * @param event the event who triggered the method
     */
    @FXML
    private void handlePause() { // NOPMD: Called by JavaFX
        ctrl.pauseSource();

        btnPlay.setDisable(false);
        btnPause.setDisable(true);
        btnStop.setDisable(false);
    }

    /**
     * Notify his app controller to stop all the sources.
     * 
     * @param event the event who triggered the method
     */
    @FXML
    private void handleStop() { // NOPMD: Called by JavaFX
        ctrl.stopSource();

        btnImport.setDisable(false);
        btnPlay.setDisable(false);
        btnPause.setDisable(true);
        btnStop.setDisable(true);
        cmbSongs.setDisable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateComboBox(final List<Buffer> list) {
        cmbSongs.getItems().clear();
        cmbSongs.getItems().addAll(list);

        if (!cmbSongs.getItems().isEmpty()) {
            cmbSongs.getSelectionModel().select(0);
        }
    }
}
