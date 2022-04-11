package controller;

import controller.view.SourceControllerView;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
import javafx.scene.control.RadioButton;
import model.source.FRSource;
import model.source.FRSourceImpl;
import model.source.SourceFactoryImpl;
import model.source.SourceType;
import view.utility.TypeSprite;

public class SourceController {

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
        switch (type) {
            case "rbtnDefault":
                speaker.setType(SourceType.FULL);
                this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCEFULL);
                break;
            case "rbtnTweeter":
                speaker.setType(SourceType.HIGH);
                this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCEHIGH);
                break;
            case "rbtnMidRange":
                speaker.setType(SourceType.MID);
                this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCEMID);
                break;
            case "rbtnWoofer":
                speaker.setType(SourceType.LOW);
                this.mainCtr.getEnvironmentController().upgradeSourceType(TypeSprite.SOURCELOW);
                break;
            default:
                break;
        }
        //change TypeSprite
    }

    /**
     * 
     * @return
     */
    public FRSource getSelectedSpeaker() {
        switch (this.selectedSource.getType()) {
            case FULL:
                controllerView.setSelectedRadioButton("rbtnDefault");
                break;
            case HIGH:
                controllerView.setSelectedRadioButton("rbtnTweeter");
                break;
            case MID:
                controllerView.setSelectedRadioButton("rbtnMidRange");
                break;
            case LOW:
                controllerView.setSelectedRadioButton("rbtnWoofer");
                break;
            default:
                break;
            }
        return this.selectedSource;
    }

    /**
     * 
     */
    public void addSpeaker() {
        var sf = new SourceFactoryImpl();
        var source = sf.createFreqRangeSource(SourceType.FULL);
        source.generateSource(mainCtr.getSongController().getSelectedID());
        this.mainCtr.getEnvironmentController().addSourcetoSourceHub(source , TypeSprite.SOURCEFULL);
    }

    /**
     * 
     */
    public void changeSelectedSource() {
        this.selectedSource = mainCtr.getEnvironmentController().getSelectedSource();
        this.controllerView.setSelectedSpeaker();
        //TODO
    }


    //TODO update X Y

}
