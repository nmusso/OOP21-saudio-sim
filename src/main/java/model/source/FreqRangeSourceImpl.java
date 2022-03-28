package model.source;

import model.utility.Vec3f;

public class FreqRangeSourceImpl extends SourceImpl implements FreqRangeSource {

    private SourceType type;

    public FreqRangeSourceImpl(final SourceType type) {
        super();
        this.setType(type);
    }

    public FreqRangeSourceImpl(final Vec3f position, final SourceType type) {
        super(position);
        this.setType(type);
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
    public final void setType(final SourceType type) {
        this.type = type;

        switch (type) {
            case LOW:
                //TODO (waiting musso)
                break;
            case MID:
                //TODO (waiting musso)
                break;
            case HIGH:
                //TODO (waiting musso)
                break;
            default:
                break;
        }
    }

}
