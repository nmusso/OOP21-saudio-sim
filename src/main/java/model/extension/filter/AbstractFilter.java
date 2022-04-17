package model.extension.filter;

import static org.lwjgl.openal.EXTEfx.alGenFilters;

import java.util.Arrays;
import model.extension.AbstractExtension;

/**
 * Abstract class for Filter, with methods which cannot be overrided.
 *
 */
public abstract class AbstractFilter extends AbstractExtension implements Filter {
    /**
     * List containing slot attribute.
     */
    private final int[] filter = new int[1];

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
