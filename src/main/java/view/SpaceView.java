package view;

import java.util.List;

/**
 * Interface for SpaceViewController.
 */
public interface SpaceView {

    /**
     * Set value passed by the controller to both the global variable and the spinner .
     * @param x width
     */
    void setX(double x);

    /**
    * Set value passed by the controller to both the global variable and the spinner Y.
    * @param y height
    */
    void setY(double y);

    /**
     * Disable/enable the combobox with the presets.
     * @param newState the new state of the combobox
     */
    void disableCombo(boolean newState);

    /**
     * Add String preset to comboBox.
     * @param listPreset from json
     */
    void addPresetTocmb(List<String> listPreset);
}
