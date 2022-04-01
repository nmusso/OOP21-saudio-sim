package model.effect;

import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL11.alSource3i;
import static org.lwjgl.openal.EXTEfx.AL_AUXILIARY_SEND_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_FILTER_NULL;
import static org.lwjgl.openal.EXTEfx.alGenAuxiliaryEffectSlots;
import static org.lwjgl.openal.EXTEfx.alGenEffects;

import java.nio.IntBuffer;

import model.source.Source;

public abstract class AbstractEffect implements Effect {
    /**
     * Buffer containing slots attributes.
     */
    private IntBuffer slot;

    /**
     * Buffer containing effect attributes.
     */
    private IntBuffer effect;

    /**
     * Set the effect in the slot on the source.
     * @param source  the source on which apply the effect
     * @param slot  the slot containing the final effect
     * @param num  the number of the effect (from enum)
     */
    protected void setOnSource(final Source source, final int slot, final int num) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, AL_FILTER_NULL);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, slot, num, AL_FILTER_NULL);
    }

    /**
     * Initialise the slot and effect buffers.
     */
    protected void initEffectBuffer() {
        slot = IntBuffer.allocate(1);
        effect = IntBuffer.allocate(1);

        alGenAuxiliaryEffectSlots(slot);
        alGenEffects(effect);
    }

    /**
     * {@link AbstractEffect#slot}.
     */
    protected IntBuffer getSlot() {
        return slot;
    }

    /**
     * {@link AbstractEffect#effect}.
     */
    protected IntBuffer getEffect() {
        return effect;
    }
}
