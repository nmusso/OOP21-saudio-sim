package model.extension.effect;

import model.extension.Extension;
import model.source.Source;
import static org.lwjgl.openal.AL11.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTEfx.*;

import java.nio.IntBuffer;

public class Effect implements Extension {

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final ALEffect alEffect, final Source source, final float val) {
        final IntBuffer slot = IntBuffer.allocate(1);
        final IntBuffer effect = IntBuffer.allocate(1);

        alGenAuxiliaryEffectSlots(slot);
        alGenEffects(effect);

        alEffecti(effect.get(0), AL_EFFECT_TYPE, alEffect.getEffect());
        alEffectf(effect.get(0), alEffect.getAttribute(), val);
        alAuxiliaryEffectSloti(slot.get(0), AL_EFFECTSLOT_EFFECT, effect.get(0));

        alSourcei(source.getId(), AL_DIRECT_FILTER, AL_FILTER_NULL);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, slot.get(0), 0, AL_FILTER_NULL);
    }

}
