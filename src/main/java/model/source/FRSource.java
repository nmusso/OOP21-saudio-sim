package model.source;

/**
 * Frequency Range Source Interface.
 * Extension of Source that adds methods for manage the frequency range.
 *
 */
public interface FRSource extends Source {

    /**
     * Returns the source type.
     * 
     * @return type
     */
    SourceType getType();

    /**
     * Sets the source type.
     * 
     * @param type the type of the source
     */
    void setType(SourceType type);
}
