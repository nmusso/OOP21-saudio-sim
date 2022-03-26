package model.source;

public interface FreqRangeSource extends Source {

    /**
     * Returns the source type.
     * 
     * @return type
     */
    SourceType getType();

    /**
     * Sets the source type.
     * 
     * @param type
     */
    void setType(SourceType type);
}
