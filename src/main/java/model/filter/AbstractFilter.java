package model.filter;

import model.source.Source;

import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL11.alSource3i;
import static org.lwjgl.openal.EXTEfx.*;

import java.nio.IntBuffer;

public abstract class AbstractFilter implements Filter {
    /**
     * Buffer containing slots attributes.
     */
    private IntBuffer filter;

    /**
     * Apply the filter on the source.
     * @param source  the source
     */
    protected void setOnSource(final int filter, final Source source) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, filter);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, AL_EFFECT_NULL, 0, filter);
    }

    /**
     * Initialise the filter buffers.
     */
    protected void initFilterBuffer() {
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
