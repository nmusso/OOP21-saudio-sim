package controller;

import controller.view.SourceControllerView;
import model.source.FreqRangeSource;
import model.source.SourceType;

public class SourceController {

    private final MainController mainCtr;
    private SourceControllerView sourceController;

    public SourceController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * 
     * @param sourceController
     */
    public void setControllerView(final SourceControllerView sourceController) {
        this.sourceController = sourceController;
    }

    /**
     * 
     * @param speaker
     * @param type
     */
    public void setSpeakerType(final FreqRangeSource speaker, final String type) {
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
    public FreqRangeSource getSelectedSpeaker() {
        return mainCtr.getEnvironmentController().getSelectedSource();
    }

}
