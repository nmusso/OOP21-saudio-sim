package model.effect;

import model.source.Source;
import static org.lwjgl.openal.AL11.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTEfx.*;

public class EffectImpl extends AbstractEffect {
    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final ALEffects alEffect, final Source source, final float val) {
        initEffectBuffer();
        final var effect = getEffect();
        final var slot = getSlot();

        alEffecti(effect.get(0), AL_EFFECT_TYPE, alEffect.getEffect());
        alEffectf(effect.get(0), alEffect.getAttribute(), val);
        alAuxiliaryEffectSloti(slot.get(0), AL_EFFECTSLOT_EFFECT, effect.get(0));

        setOnSource(source, slot.get(0), alEffect.getNumber());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final ALEffects alEffect, final Source source) {
        initEffectBuffer();
        final var effect = getEffect();
        final var slot = getSlot();

        alEffecti(effect.get(0), AL_EFFECT_TYPE, AL_EFFECT_NULL);
        alAuxiliaryEffectSloti(slot.get(0), AL_EFFECTSLOT_EFFECT, effect.get(0));

        setOnSource(source, slot.get(0), alEffect.getNumber());
    }
}
