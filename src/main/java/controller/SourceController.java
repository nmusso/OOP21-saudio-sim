package controller;

import controller.view.SourceControllerView;
import model.source.FRSource;
import model.source.SourceFactoryImpl;
import model.source.SourceType;
import view.utility.TypeSprite;

public class SourceController implements ControllerApplication<SourceControllerView> {

    private final MainController mainCtr;
    private SourceControllerView controllerView;
    private FRSource selectedSource;

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
        //TODO clear
        switch (type) {
        case "rbtnDefault":
            speaker.setType(SourceType.FULL);
            this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCEFULL);
            this.controllerView.updatePieChartFreq(33, 33, 33);
            break;
        case "rbtnTweeter":
            speaker.setType(SourceType.HIGH);
            this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCEHIGH);
            this.controllerView.updatePieChartFreq(70, 15, 15);
            break;
        case "rbtnMidRange":
            speaker.setType(SourceType.MID);
            this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCEMID);
            this.controllerView.updatePieChartFreq(25, 50, 25);
            break;
        case "rbtnWoofer":
            speaker.setType(SourceType.LOW);
            this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCELOW);
            this.controllerView.updatePieChartFreq(15, 10, 75);
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
        return this.selectedSource;
    }

    /**
     * 
     */
    public void addSpeaker() {
        // TODO clean
        var sf = new SourceFactoryImpl();
        var source = sf.createFreqRangeSource(SourceType.FULL);
        source.generateSource(mainCtr.getSongController().getSelectedID());
        this.mainCtr.getEnvironmentController().addSourcetoSourceHub(source, TypeSprite.SOURCEFULL);
    }

    /**
     * 
     */
    public void removeSpeaker() {
        this.mainCtr.getEnvironmentController().removeSource(this.selectedSource);
    }

    /**
     * 
     */
    public void changeSelectedSource() {
        this.selectedSource = mainCtr.getEnvironmentController().getSelectedSource();
        this.controllerView.updateSelectedSpeaker();
    }

}
