package model.effect;

import model.source.Source;

/**
 * Interface for the effect applier.
 *
 */
public interface Effect {
    /**
     * Apply the effect on the source.
     * @param effect  the effect to be applied
     * @param source  the source on which will be applied
     * @param val  the "volume" of the effect 
     */
    void apply(ALEffects effect, Source source, float val);

    /**
     * Remove the effect from the source.
     * @param effect  the effect to be removed
     * @param source  the source with the effect
     */
    void remove(ALEffects effect, Source source);
}
