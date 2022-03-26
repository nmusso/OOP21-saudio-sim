package model.source;

public abstract class FreqRangeSourceImpl extends SourceImpl implements FreqRangeSource {

    private SourceType type;

    @Override
    public SourceType getType() {
        return type;
    }

}
