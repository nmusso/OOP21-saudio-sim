package model.filter;

import model.source.Source;
import model.source.SourceType;

public interface Filter {

    /**
     * Value for Lowpass filter.
     */
    float LOWPASS_VALUE = 0.3f;
    /**
     * Value for Bandpass filter.
     */
    float BANDPASS_VALUE = 0.3f;
    /**
     * Value for Highpass filter.
     */
    float HIGHPASS_VALUE = 0.6f;

    /**
     * Apply the filter type (high, medium, band) on the source.
     * @param source  the source
     * @param type  the type of the filter
     */
    void applyFilter(Source source, SourceType type);

    /**
     * Remove the filter from the source.
     * @param source  the source
     */
    void removeFilter(Source source);
}
