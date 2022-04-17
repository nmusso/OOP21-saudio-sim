package model.extension.effect;

import static org.lwjgl.openal.EXTEfx.alGenAuxiliaryEffectSlots;
import static org.lwjgl.openal.EXTEfx.alGenEffects;

import java.util.Arrays;
import model.extension.AbstractExtension;

/**
 * Abstract class for Effect, extended from AbstractExtension.
 */
public abstract class AbstractEffect extends AbstractExtension implements Effect {
    /**
     * List containing slot attribute.
     */
    private final int[] slot = new int[1];

    /**
     * List containing effect attribute.
     */
    private final int[] effect = new int[1];

    /**
     * Initialise the slot and effect buffers.
     */
    protected final void initBuffers() {
        alGenAuxiliaryEffectSlots(slot);
        alGenEffects(effect);
    }

    /**
     * @return {@link AbstractEffect#slot}.
     */
    protected int[] getSlot() {
        return Arrays.copyOf(slot, 1);
    }

    /**
     * @return {@link AbstractEffect#effect}.
     */
    protected int[] getEffect() {
        return Arrays.copyOf(effect, 1);
    }
}
