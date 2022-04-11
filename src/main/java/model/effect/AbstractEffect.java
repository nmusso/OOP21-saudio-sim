package model.effect;

import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL11.alSource3i;
import static org.lwjgl.openal.EXTEfx.AL_AUXILIARY_SEND_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_FILTER_NULL;
import static org.lwjgl.openal.EXTEfx.alGenAuxiliaryEffectSlots;
import static org.lwjgl.openal.EXTEfx.alGenEffects;

import java.nio.IntBuffer;
import java.util.Arrays;

import model.source.Source;

/**
 * Abstract class for Effect, with methods which cannot be overrided.
 *
 */
public abstract class AbstractEffect implements Effect {
    /**
     * Buffer containing slots attributes.
     */
    private int[] slot = new int[1];

    /**
     * Buffer containing effect attributes.
     */
    private int[] effect = new int[1];

    /**
     * Set the effect in the slot on the source.
     * @param source  the source on which apply the effect
     * @param slot  the slot containing the final effect
     * @param num  the number of the effect (from enum)
     */
    protected final void setOnSource(final Source source, final int slot, final int num) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, AL_FILTER_NULL);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, slot, num, AL_FILTER_NULL);
    }

    /**
     * Initialise the slot and effect buffers.
     */
    protected final void initEffectBuffer() {

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
