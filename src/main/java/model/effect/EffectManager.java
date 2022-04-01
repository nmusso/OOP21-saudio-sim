package model.effect;

import model.source.Source;
import static org.lwjgl.openal.AL11.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTEfx.*;

import java.nio.IntBuffer;

public class EffectManager implements Effect {

    private IntBuffer slot;
    private IntBuffer effect;
    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final ALEffect alEffect, final Source source, final float val) {
        initEffectBuffer();

        alEffecti(effect.get(0), AL_EFFECT_TYPE, alEffect.getEffect());
        alEffectf(effect.get(0), alEffect.getAttribute(), val);
        alAuxiliaryEffectSloti(slot.get(0), AL_EFFECTSLOT_EFFECT, effect.get(0));

        setOnSource(source, slot.get(0), alEffect.getNumber());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final ALEffect alEffect, final Source source) {
        initEffectBuffer();

        alEffecti(effect.get(0), AL_EFFECT_TYPE, AL_EFFECT_NULL);
        alAuxiliaryEffectSloti(slot.get(0), AL_EFFECTSLOT_EFFECT, effect.get(0));

        setOnSource(source, slot.get(0), alEffect.getNumber());
    }

    private void setOnSource(final Source source, final int slot, final int num) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, AL_FILTER_NULL);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, slot, num, AL_FILTER_NULL);
    }

    private void initEffectBuffer() {
        slot = IntBuffer.allocate(1);
        effect = IntBuffer.allocate(1);

        alGenAuxiliaryEffectSlots(slot);
        alGenEffects(effect);
    }
}
