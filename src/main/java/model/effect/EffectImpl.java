package model.effect;

import model.source.Source;
import static org.lwjgl.openal.EXTEfx.alEffecti;
import static org.lwjgl.openal.EXTEfx.alEffectf;
import static org.lwjgl.openal.EXTEfx.alAuxiliaryEffectSloti;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_TYPE;
import static org.lwjgl.openal.EXTEfx.AL_EFFECTSLOT_EFFECT;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_NULL;

/**
 * Extension of AbstractEffect, with methods apply and remove.
 *
 */
public class EffectImpl extends AbstractEffect {
    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final ALEffects alEffect, final Source source, final float val) {
        initEffectBuffer();
        final int effect = getEffect()[0];
        final int slot = getSlot()[0];

        alEffecti(effect, AL_EFFECT_TYPE, alEffect.getEffect());
        alEffectf(effect, alEffect.getAttribute(), val);
        alAuxiliaryEffectSloti(slot, AL_EFFECTSLOT_EFFECT, effect);

        setOnSource(source, slot, alEffect.ordinal());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final ALEffects alEffect, final Source source) {
        initEffectBuffer();
        final var effect = getEffect();
        final var slot = getSlot();

        alEffecti(effect[0], AL_EFFECT_TYPE, AL_EFFECT_NULL);
        alAuxiliaryEffectSloti(slot[0], AL_EFFECTSLOT_EFFECT, effect[0]);

        setOnSource(source, slot[0], alEffect.ordinal());
    }
}
