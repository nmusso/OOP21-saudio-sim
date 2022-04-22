package model.extension;

import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL11.alSource3i;
import static org.lwjgl.openal.EXTEfx.AL_AUXILIARY_SEND_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;

import model.source.Source;

/**
 * Abstract class for Extensions.
 */
public abstract class AbstractExtension implements Extension {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setOnSource(final Source source, final int filter, final int slot, final int num) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, filter);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, slot, num, filter);
    }

    /**
     * Initialise the buffers.
     */
    protected abstract void initBuffers();
}
