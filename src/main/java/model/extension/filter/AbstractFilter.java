package model.extension.filter;

import static org.lwjgl.openal.EXTEfx.alGenFilters;

import java.util.Arrays;
import model.extension.AbstractExtension;

/**
 * Abstract class for Filter, extended from AbstractExtension.
 *
 */
public abstract class AbstractFilter extends AbstractExtension implements Filter {
    /**
     * Number of simultaneously filter supported in the buffer.
     */
    private static final int NUM_SIMUL_FILTER = 1;
    /**
     * List containing slot attribute.
     */
    private final int[] filter = new int[NUM_SIMUL_FILTER];

    /**
     * Initialise the filter buffers.
     */
    protected final void initBuffers() {
        alGenFilters(filter);
    }

    /**
     * @return {@link AbstractFilter#filter}.
     */
    public int[] getFilter() {
        return Arrays.copyOf(filter, 1);
    }
}
