package controller;

import controller.view.SourceControllerView;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
import model.source.FRSource;
import model.source.FRSourceImpl;
import model.source.SourceType;

public class SourceController {

    private final MainController mainCtr;
    private SourceControllerView controllerView;

    public SourceController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * 
     * @param sourceController
     */
    public void setControllerView(final SourceControllerView sourceController) {
        this.controllerView = sourceController;
    }

    /**
     * 
     * @param speaker
     * @param type
     */
    public void setSpeakerType(final FRSource speaker, final String type) {
        switch (type) {
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

    /**
     * 
     * @return
     */
    public FRSource getSelectedSpeaker() {
        return mainCtr.getEnvironmentController().getSelectedSource();
    }

}
