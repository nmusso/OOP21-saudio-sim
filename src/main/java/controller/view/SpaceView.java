package controller.view;

public interface SpaceView {

    /**
     * Set value passed by the controller to both the global variable and the spinner .
     * @param x
     */
    void setX(double x);

    /**
    * Set value passed by the controller to both the global variable and the spinner Y.
    * @param y
    */
    void setY(double y);

    /**
     * Disable/enable the combobox with the presets.
     * @param newState the new state of the combobox
     */
    void disableCombo(boolean newState);

}