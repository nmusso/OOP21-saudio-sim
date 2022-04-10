package controller.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import controller.SourceController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import model.source.FreqRangeSource;
import model.source.SourceType;

public class SourceControllerView implements Initializable, ControllerView {
    @FXML private Button btnAddSpeaker;
    @FXML private Button btnRemoveSpeaker;
    @FXML private RadioButton rbtnDefault;
    @FXML private RadioButton rbtnTweeter;
    @FXML private RadioButton rbtnMidRange;
    @FXML private RadioButton rbtnWoofer;
    private SourceController ctrSource;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrSource = ctrMain.getSourceController();
        this.ctrSource.setControllerView(this);
    }

    @FXML private void handleAddSpeaker(final Event event) {
        //TODO Add speaker from SourceHub in Environment
    }

    @FXML private void handleRemoveSpeaker(final Event event) {
        FreqRangeSource speaker = this.getSelectedSpeaker();

        //TODO Remove speaker from SourceHub in Environment
    }

    @FXML private void handleRadioButtonChanged(final Event event) {
        ctrSource.setSpeakerType(this.getSelectedSpeaker(), ((RadioButton) event.getSource()).getId());
    }

    private FreqRangeSource getSelectedSpeaker() {
        return this.ctrSource.getSelectedSpeaker();
    }


}
