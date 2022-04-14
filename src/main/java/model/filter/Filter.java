package model.filter;

import model.source.Source;
import model.source.SourceType;

/**
 * Interface for the filter applier.
 *
 */
public interface Filter {

    /**
     * Value for Lowpass filter.
     */
    float LOWPASS_VALUE = 0.33f;
    /**
     * Value for Bandpass filter.
     */
    float BANDPASS_VALUE = 0.33f;
    /**
     * Value for Highpass filter.
     */
    float HIGHPASS_VALUE = 0.33f;

    /**
     * Apply the filter type (high, medium, band) on the source.
     * 
     * @param source the source
     * @param type   the type of the filter
     */
    void applyFilter(Source source, SourceType type);

    /**
     * Remove the filter from the source.
     * 
     * @param source the source
     */
    void removeFilter(Source source);
}
