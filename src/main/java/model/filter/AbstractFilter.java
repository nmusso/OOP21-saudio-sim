package model.filter;

import model.source.Source;

import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL11.alSource3i;
import static org.lwjgl.openal.EXTEfx.alGenFilters;
import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_AUXILIARY_SEND_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_NULL;

import java.nio.IntBuffer;

/**
 * Abstract class for Filter, with methods which cannot be overrided.
 *
 */
public abstract class AbstractFilter implements Filter {
    /**
     * Buffer containing slots attributes.
     */
    private IntBuffer filter;

    /**
     * Apply the filter on the source.
     * @param source  the source
     */
    protected final void setOnSource(final int filter, final Source source) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, filter);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, AL_EFFECT_NULL, 0, filter);
    }

    /**
     * Initialise the filter buffers.
     */
    protected final void initFilterBuffer() {
        filter = IntBuffer.allocate(1);

        alGenFilters(filter);
    }

    /**
     * @return {@link AbstractFilter#filter}.
     */
    public IntBuffer getFilter() {
        return filter;
    }
}
