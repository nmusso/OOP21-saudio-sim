package controller;

import java.util.Optional;
import java.util.function.Function;

import controller.view.SourceControllerView;
import model.source.FRSource;
import model.source.SourceFactory;
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
     * Sets the controller View.
     * 
     * @param sourceController
     */
    public void setControllerView(final SourceControllerView sourceController) {
        this.controllerView = sourceController;
    }

    /**
     * Sets the speaker type.
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
        this.updatePieChartData();
    }

    /**
     * Gets the selected speaker from the environment.
     * 
     * @return selectedSource
     */
    public FRSource getSelectedSpeaker() {
        return this.selectedSource;
    }

    /**
     * Updates the selected source.
     * 
     */
    public void updateSelectedSource() {
        Optional<FRSource> s =  mainCtr.getEnvironmentController().getSelectedSource();
        if (s.isPresent()) {
            this.selectedSource = s.get();
            this.controllerView.updateSelectedSpeaker();
        }
    }

    /**
     * Creates a new FRSource and add it to environment.
     * 
     */
    public void addSpeaker() {
        final SourceFactory sourceFactory = new SourceFactoryImpl();
        final FRSource newSource = sourceFactory.createFRSource(SourceType.FULL);
        newSource.generateSource(mainCtr.getSongController().getSelectedID());
        this.mainCtr.getEnvironmentController().addSourcetoSourceHub(newSource, TypeSprite.SOURCEFULL); 
    }

    /**
     * Removes the selected speaker from environment.
     * 
     */
    public void removeSpeaker() {
        this.mainCtr.getEnvironmentController().removeSource(this.selectedSource);
    }

    /**
     * Enable or disable adding a source.
     * 
     * @param isDisable
     */
    public void setDisableAddSource(final boolean isDisable) {
        this.controllerView.setDisableAddSpeaker(isDisable);
    }

    /**
     * Get the percentages for the frequencies and update the PieChart.
     * 
     */
    public void updatePieChartData() {
        final double weight = 0.33;
        final int full = getNumType(SourceType.FULL);
        final int high = getNumType(SourceType.HIGH);
        final int mid = getNumType(SourceType.MID);
        final int low = getNumType(SourceType.LOW);
        final double tot = high + mid + low + full;
        final Function<Integer, Double> percValue = (v) -> (v + (full * weight)) / tot;
        this.controllerView.updatePieChartFreq(percValue.apply(high), percValue.apply(mid), percValue.apply(low));
    }

    private int getNumType(final SourceType type) {
        return ((Long) this.mainCtr.getEnvironmentController().getEnv().getSourceHub().getAll().stream()
                                                                                          .filter(s -> s.getType().equals(type))
                                                                                          .count()).intValue();
    }
}
