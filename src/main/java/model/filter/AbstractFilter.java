package model.filter;

import model.source.Source;

import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.AL11.alSource3i;
import static org.lwjgl.openal.EXTEfx.alGenFilters;
import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_AUXILIARY_SEND_FILTER;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_NULL;

import java.util.Arrays;

/**
 * Abstract class for Filter, with methods which cannot be overrided.
 *
 */
public abstract class AbstractFilter implements Filter {
    /**
     * Buffer containing slots attributes.
     */
    private final int[] filter = new int[1];

    /**
     * Apply the filter on the source.
     * 
     * @param filter the filter
     * @param source the source
     */
    protected final void setOnSource(final int filter, final Source source) {
        alSourcei(source.getId(), AL_DIRECT_FILTER, filter);
        alSource3i(source.getId(), AL_AUXILIARY_SEND_FILTER, AL_EFFECT_NULL, 0, filter);
    }

    /**
     * Initialise the filter buffers.
     */
    protected final void initFilterBuffer() {
        alGenFilters(filter);
    }

    /**
     * @return {@link AbstractFilter#filter}.
     */
    public int[] getFilter() {
        return Arrays.copyOf(filter, 1);
    }
}
