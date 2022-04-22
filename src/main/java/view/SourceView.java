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
     * @param isSelected
     */
    void speakerIsSelected(boolean isSelected);

    /**
     * Enable or disable being able to add a speaker.
     * 
     * @param isDisable
     */
    void setDisableAddSpeaker(boolean isDisable);

    /**
     * Updates the Frequency data.
     * 
     * @param highValue
     * @param midValue
     * @param lowValue
     */
    void updateFreqData(double highValue, double midValue, double lowValue);
}
