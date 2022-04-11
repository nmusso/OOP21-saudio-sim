package model.source;

import model.filter.Filter;
import model.filter.FilterImpl;
import model.utility.Vec3f;

public class FRSourceImpl extends SourceImpl implements FRSource {

    private SourceType type;
    private Filter filter;

    public FRSourceImpl(final SourceType type) {
        super();
        this.initFilterAndType(type);
    }

    public FRSourceImpl(final Vec3f position, final SourceType type) {
        super(position);
        this.initFilterAndType(type);
    }

    private void initFilterAndType(final SourceType type) {
        filter = new FilterImpl();
        this.setType(type);
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
