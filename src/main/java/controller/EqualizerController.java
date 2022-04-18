package controller;

import static org.lwjgl.openal.AL10.alGetError;
import static org.lwjgl.openal.AL10.AL_NO_ERROR;
import controller.view.EqualizerView;
import model.extension.effect.ALEffects;

/**
 * Controller for the EqualizerView which will communicate with model and his ViewController.
 *
 */
public class EqualizerController implements ControllerApplication<EqualizerView> {

    private final MainController mainCtr;
    private EqualizerView ctrlView;

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
    public void setControllerView(final EqualizerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * Apply the effect on all the sources.
     * @param val  the value of the slider
     * @param effect  the effect
     */
    public void applyEffect(final float val, final ALEffects effect) {
       this.mainCtr.getEnvironmentController().getEnv().addEffect(effect, val);

       if (alGetError() != AL_NO_ERROR) {
           ctrlView.showError("Something went wrong while applying the effect");
       }
    }

    /**
     * Remove all the effects from all the sources.
     */
    public void removeEffect() {
        this.mainCtr.getEnvironmentController().getEnv().removeEffect();
    }
}
