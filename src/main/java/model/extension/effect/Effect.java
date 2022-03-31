package model.extension.effect;

import model.extension.Extension;
import model.source.Source;
import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_AUXILIARY_SEND_FILTER;
import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL11.alSource3i;
import java.nio.IntBuffer;

public abstract class Effect implements Extension {
    private IntBuffer filter;
    private IntBuffer slot;

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final Source source) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, filter.get(0));

        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, slot.get(0), 0, filter.get(0));
    }
}
