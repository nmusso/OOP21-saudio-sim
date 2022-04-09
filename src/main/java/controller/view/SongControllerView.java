package controller.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
}
