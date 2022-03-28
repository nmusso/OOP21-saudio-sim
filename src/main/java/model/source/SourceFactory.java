package model.source;

import model.utility.Vec3f;

public interface SourceFactory {

    /**
     * Create a basic Source at the position (0.0f, 0.0f, 0.0f).
     * 
     * @return Source
     */
    Source createSource();

    /**
     * Create a basic Source in the specified position.
     * 
     * @return Source
     */
    Source createSourceWPos(Vec3f position);

    /**
     * Create a Source with a given Frequency Range.
     * 
     * @return Source
     */
    Source createFreqRangeSource(SourceType type);

    /**
     * Create a Source with a given Frequency Range in the specified position.
     * 
     * @return Source
     */
    Source createFreqRangeSourceWPos(Vec3f position, SourceType type);
}
