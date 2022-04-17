package model.extension.effect;

import static org.lwjgl.openal.EXTEfx.alEffecti;
import static org.lwjgl.openal.EXTEfx.alEffectf;
import static org.lwjgl.openal.EXTEfx.alAuxiliaryEffectSloti;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_TYPE;
import static org.lwjgl.openal.EXTEfx.AL_EFFECTSLOT_EFFECT;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_NULL;
import static org.lwjgl.openal.EXTEfx.AL_FILTER_LOWPASS;
import static org.lwjgl.openal.EXTEfx.AL_FILTER_BANDPASS;
import static org.lwjgl.openal.EXTEfx.AL_FILTER_HIGHPASS;
import static org.lwjgl.openal.EXTEfx.AL_FILTER_NULL;

import model.source.FRSource;

/**
 * Extension of AbstractEffect, with methods apply and remove.
 *
 */
public class EffectImpl extends AbstractEffect {

    /**
     * Constructor for EffectImpl, initialise the buffer.
     */
    public EffectImpl() {
        initBuffers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final ALEffects alEffect, final FRSource source, final float val) {
        final int effect = getEffect()[0];
        final int slot = getSlot()[0];

        alEffecti(effect, AL_EFFECT_TYPE, alEffect.getEffect());
        alEffectf(effect, alEffect.getAttribute(), val);
        alAuxiliaryEffectSloti(slot, AL_EFFECTSLOT_EFFECT, effect);

        setOnSource(source, getSourceType(source), slot, alEffect.ordinal());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final ALEffects alEffect, final FRSource source) {
        final var effect = getEffect()[0];
        final var slot = getSlot()[0];

        alEffecti(effect, AL_EFFECT_TYPE, AL_EFFECT_NULL);
        alAuxiliaryEffectSloti(slot, AL_EFFECTSLOT_EFFECT, effect);

        setOnSource(source, getSourceType(source), slot, alEffect.ordinal());
    }

    /**
     * Get the type of the source as AL constant.
     * @param source the source
     * @return the type of the source
     */
    private int getSourceType(final FRSource source) {
        switch (source.getType()) {
        case LOW:
            return AL_FILTER_LOWPASS;
        case MID:
            return AL_FILTER_BANDPASS;
        case HIGH:
            return AL_FILTER_HIGHPASS;
        default:
            return AL_FILTER_NULL;
        }
    }
}
