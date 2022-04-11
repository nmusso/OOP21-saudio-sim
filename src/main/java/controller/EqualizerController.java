package controller;

import controller.view.EqualizerControllerView;
import model.effect.ALEffects;

/**
 * Controller for the EqualizerView which will communicate with model and his ViewController.
 *
 */
public class EqualizerController {

    private final MainController mainCtr;
    private EqualizerControllerView ctrlView;

    /**
     * Constructor of the EqualizerController.
     * @param mainCtr  the main controller
     */
    public EqualizerController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * Set the controller of the view.
     * @param controllerView  the controller view
     */
    public void setControllerView(final EqualizerControllerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * Apply the effect on all the sources.
     * @param val  the value of the slider
     * @param effect  the effect
     */
    public void applyEffect(final float val, final ALEffects effect) {

    }

    /**
     * Remove all the effects from all the sources.
     */
    public void removeEffect() {

    }
}
