package view;

import view.controller.ControllerView;

/**
 * Source View related to the control of the speakers.
 *
 */
public interface SourceView extends ControllerView {

    /**
     * Updates the selected speaker.
     */
    void updateSelectedSpeaker();

    /**
     * Enable or disable being able to manage the speaker.
     * 
     * @param isSelected true if the speaker is selected, false otherwise
     */
    void speakerIsSelected(boolean isSelected);

    /**
     * Enable or disable being able to add a speaker.
     * 
     * @param isDisable true if it should disabled, false otherwise
     */
    void setDisableAddSpeaker(boolean isDisable);

    /**
     * Updates the Frequency data.
     * 
     * @param highValue percentage of high speakers
     * @param midValue percentage of mid speakers
     * @param lowValue percentage of low speakers
     */
    void updateFreqData(double highValue, double midValue, double lowValue);
}
