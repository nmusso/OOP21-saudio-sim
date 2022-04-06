package model.source;

import model.filter.Filter;
import model.utility.Vec3f;

public class FreqRangeSourceImpl extends SourceImpl implements FreqRangeSource {

    private SourceType type;
    private Filter filter;

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
        filter.applyFilter(this, type);
    }

}
