package controller.view;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import controller.MainController;
import controller.SongController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Controller of the view SongView.
 *
 */
public class SongControllerView implements Initializable, ControllerView {

    private static final String SEP = System.getProperty("file.separator");
    @FXML private Button btnPlay;
    @FXML private Button btnPause;
    @FXML private Button btnStop;
    @FXML private ComboBox<String> cmbSongs;
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
        addStartSongs();
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
                ctrl.createBuffer(file);
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
        final String id = Character.toString(cmbSongs.getSelectionModel().getSelectedItem().charAt(0));
        final int bufferID = Integer.parseInt(id);
        ctrl.playSource(bufferID);

        btnPlay.setDisable(true);
        btnPause.setDisable(false);
        btnStop.setDisable(false);
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
        btnPlay.setDisable(false);
        btnPause.setDisable(true);
        btnStop.setDisable(true);
    }

    /**
     * Update the items of the combobox.
     */
    private void updateComboBox() {
        cmbSongs.getItems().clear();
        cmbSongs.getItems().addAll(ctrl.getBufferList());

        if (!cmbSongs.getItems().isEmpty()) {
            cmbSongs.getSelectionModel().select(0);
        }
    }

    /**
     * Import automatically all the wav in the resources path.
     */
    private void addStartSongs() {
        final String folderPath = "src" + SEP + "main" + SEP + "resources" + SEP + "songs" + SEP;
        final File folder = new File(folderPath);

        if (folder.exists()) {
            final var files = folder.listFiles();

            if (files != null) {
                for (final File file : files) {
                    if (!file.isDirectory()) {
                        ctrl.createBuffer(file);
                    }
                }
            }
        }

        updateComboBox();
    }
    
    public ComboBox<String> getCmbSongs() {
        return cmbSongs;
    }

    @Override
    public void showError(String error) {
        // TODO Auto-generated method stub
        
    }

}
