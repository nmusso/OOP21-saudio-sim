package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class SourceController implements Initializable {
    @FXML private Button btnAddSpeaker;
    @FXML private Button btnRemoveSpeaker;
    @FXML private RadioButton rbtnDefault;
    @FXML private RadioButton rbtnTweeter;
    @FXML private RadioButton rbtnMidRange;
    @FXML private RadioButton rbtnWoofer;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        System.out.println("SourceController: hello!");
        //TODO
    }

    @FXML private void handleAddSpeaker(final Event event) {
        System.out.println("SourceController: AddSpeaker()");
      //TODO
    }

    @FXML private void handleRemoveSpeaker(final Event event) {
        System.out.println("SourceController: RemoveSpeaker()");
        //btnAddSpeaker.setDisable(true);
        //TODO
    }

    @FXML private void handleRadioButtonChanged(final Event event) {
        System.out.println("SourceController: RadioButtonSelected()");

        switch (((RadioButton) event.getSource()).getId()) {
            case "rbtnDefault":
                //TODO
                break;
            case "rbtnTweeter":
                //TODO
                break;
            case "rbtnMidRange":
                //TODO
                break;
            case "rbtnWoofer":
                //TODO
                break;
            default:
                break;
        }

    }
}
