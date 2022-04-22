package model.source;

import model.utility.Vec3f;

/**
 * Interface of Factory for Sources and FRSources with parameters variation.
 *
 */
public interface SourceFactory {

    /**
     * Create a basic Source at the position (0.0f, 0.0f, 0.0f).
     * 
     * @return Source at the position (0.0f, 0.0f, 0.0f)
     */
    Source createSource();

    /**
     * Create a basic Source in the specified position.
     * 
     * @param position
     * @return basic Source in the specified position
     */
    Source createSourceWithPosition(Vec3f position);

    /**
     * Create a FreqRangeSource with default Frequency Range (FULL).
     * 
     * @return Source with a given Frequency Range
     */
    FRSource createDefaultFRSource();

    /**
     * Create a FRSource with a given Frequency Range.
     * 
     * @param type
     * @return FRSource with a given Frequency Range
     */
    FRSource createFRSource(SourceType type);

    /**
     * Create a FRSource with a given Frequency Range in the specified position.
     * 
     * @param position
     * @param type
     * @return FRSource with a given Frequency Range in the specified position
     */
    FRSource createFRSourceWithPosition(Vec3f position, SourceType type);
}
