package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import model.source.FreqRangeSource;
import model.source.SourceType;

public class SourceController implements Initializable, ControllerView {
    @FXML private Button btnAddSpeaker;
    @FXML private Button btnRemoveSpeaker;
    @FXML private RadioButton rbtnDefault;
    @FXML private RadioButton rbtnTweeter;
    @FXML private RadioButton rbtnMidRange;
    @FXML private RadioButton rbtnWoofer;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @FXML private void handleAddSpeaker(final Event event) {
        //TODO Add speaker from SourceHub in Environment
    }

    @FXML private void handleRemoveSpeaker(final Event event) {
        FreqRangeSource speaker = this.getSelectedSpeaker();

        //TODO Remove speaker from SourceHub in Environment
    }

    @FXML private void handleRadioButtonChanged(final Event event) {
        FreqRangeSource speaker = this.getSelectedSpeaker();

        switch (((RadioButton) event.getSource()).getId()) {
            case "rbtnDefault":
                speaker.setType(SourceType.FULL);
                break;
            case "rbtnTweeter":
                speaker.setType(SourceType.HIGH);
                break;
            case "rbtnMidRange":
                speaker.setType(SourceType.MID);
                break;
            case "rbtnWoofer":
                speaker.setType(SourceType.LOW);
                break;
            default:
                break;
        }
    }

    private void speakerSelected(final  FreqRangeSource speaker) {
        //rbtnWoofer.setSelected(true);
    }

    private FreqRangeSource getSelectedSpeaker() {
        return null;
    }

    @Override
    public void setControllerApplication(final MainControllerApplication ctrMain) {
        // TODO Auto-generated method stub

    }
}
