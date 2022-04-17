package controller;

import controller.view.EqualizerControllerView;
import model.extension.effect.ALEffects;

/**
 * Controller for the EqualizerView which will communicate with model and his ViewController.
 *
 */
public class EqualizerController implements ControllerApplication<EqualizerControllerView> {

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
     * {@inheritDoc}
     */
    @Override
    public void setControllerView(final EqualizerControllerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * Apply the effect on all the sources.
     * @param val  the value of the slider
     * @param effect  the effect
     */
    public void applyEffect(final float val, final ALEffects effect) {
       this.mainCtr.getEnvironmentController().getEnv().addEffect(effect, val);
    }

    /**
     * Remove all the effects from all the sources.
     */
    public void removeEffect() {
        this.mainCtr.getEnvironmentController().getEnv().removeEffect();
    }
}
