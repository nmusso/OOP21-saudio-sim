package model.source;

import model.utility.Vec3f;

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
     * @return Source in the specified position
     */
    Source createSourceWithPos(Vec3f position);

    /**
     * Create a FreqRangeSource with default Frequency Range (FULL).
     * 
     * @return Source with a given Frequency Range
     */
    FRSource createFreqRangeSource();

    /**
     * Create a FreqRangeSource with a given Frequency Range.
     * 
     * @return Source with a given Frequency Range
     */
    FRSource createFreqRangeSource(SourceType type);

    /**
     * Create a FreqRangeSource with a given Frequency Range in the specified position.
     * 
     * @return Source with a given Frequency Range in the specified position
     */
    FRSource createFreqRangeSourceWithPos(Vec3f position, SourceType type);
}
