package model.source;

import model.utility.Vec3f;

public abstract class FreqRangeSourceImpl extends SourceImpl implements FreqRangeSource {

    private SourceType type;

    public FreqRangeSourceImpl(final Vec3f position, final SourceType type) {
        super(position);
        setType(type);
    }

    /**
     * @inheritDoc
     */
    @Override
    public SourceType getType() {
        return type;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setType(final SourceType type) {
        this.type = type;

        switch (type) {
            case LOW:
                //TODO
                break;
            case MID:
                //TODO
                break;
            case HIGH:
                //TODO
                break;
            default:
                break;
        }
    }

}
