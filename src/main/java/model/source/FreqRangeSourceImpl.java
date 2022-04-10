package model.source;

import model.filter.Filter;
import model.filter.FilterImpl;
import model.utility.Vec3f;

public class FreqRangeSourceImpl extends SourceImpl implements FreqRangeSource {

    private SourceType type;
    private Filter filter;

    public FreqRangeSourceImpl(final SourceType type) {
        super();
        this.initFilterAndType(type);
    }

    public FreqRangeSourceImpl(final Vec3f position, final SourceType type) {
        super(position);
        this.initFilterAndType(type);
    }

    private void initFilterAndType(final SourceType type) {
        this.setType(type);
        filter = new FilterImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    public SourceType getType() {
        return this.type;
    }

    /**
     * @inheritDoc
     */
    @Override
    public final void setType(final SourceType type) {
        this.type = type;

        if (type.equals(SourceType.FULL)) {
            this.filter.removeFilter(this);
        } else {
            this.filter.applyFilter(this, type);
        }
    }

}
