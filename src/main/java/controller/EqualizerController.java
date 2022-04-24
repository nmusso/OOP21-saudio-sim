package controller;

import static org.lwjgl.openal.AL10.alGetError;

import java.util.Optional;

import static org.lwjgl.openal.AL10.AL_NO_ERROR;

import model.extension.effect.ALEffects;
import view.EqualizerView;

/**
 * Controller which communicate with model and EqualizerView.
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
           ctrlView.showMessage("Something went wrong while applying the effect");
       }
    }

    /**
     * Remove all the effects from all the sources.
     */
    public void removeEffect() {
        this.mainCtr.getEnvironmentController().getEnv().removeEffect();
    }

    /**
     * Get the effect associated to the slider.
     * 
     * @param id the id of the slider
     * @return an optional containing the effect, or empty if there isn't an effect
     *         associated to the id
     */
    public Optional<ALEffects> getEffect(final String id) {
        switch (id) {
        case "sldReverb":
            return Optional.of(ALEffects.REVERB);
        case "sldEcho":
            return Optional.of(ALEffects.ECHO);
        case "sldFlanger":
            return Optional.of(ALEffects.FLANGER);
        case "sldAutowah":
            return Optional.of(ALEffects.AUTOWAH);
        case "sldChorus":
            return Optional.of(ALEffects.CHORUS);
        default:
            return Optional.empty();
        }
    }
}
