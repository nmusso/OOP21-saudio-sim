package controller.view;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.Slider;
import model.extension.effect.ALEffects;

/**
 * Interface for EqualizerViewController.
 */
public interface EqualizerView extends ControllerView {

    /**
     * Get the effect associated to the slider.
     * @param id  the id of the slider
     * @return an optional containing the effect, or empty if there isn't an effect associated to the id
     */
    Optional<ALEffects> getEffect(String id);

    /**
     * Get all the sliders.
     * @return a list of sliders
     */
    List<Slider> getSliders();

    /**
     * Set the property of the sliders.
     */
    void initSliders();
}
