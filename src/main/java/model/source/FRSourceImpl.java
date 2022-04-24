package model.source;

import java.util.Objects;

import model.extension.filter.Filter;
import model.extension.filter.FilterImpl;
import model.utility.Vec3f;

/**
 * Frequency Range Source.
 * Extension of Source that adds methods for manage the frequency range.
 *
 */
public class FRSourceImpl extends SourceImpl implements FRSource {

    private SourceType type;
    private Filter filter;

    /**
     * Construct a new FRSource with the given type.
     * @param type the type of the source
     */
    public FRSourceImpl(final SourceType type) {
        super();
        this.initFilterAndType(type);
    }

    /**
     * Construct a new FRSource with the given type and position.
     * @param position the position of the source
     * @param type the type of the source
     */
    public FRSourceImpl(final Vec3f position, final SourceType type) {
        super(position);
        this.initFilterAndType(type);
    }

    private void initFilterAndType(final SourceType type) {
        filter = new FilterImpl();
        if (!type.equals(SourceType.FULL)) {
            this.setType(type);
        } else {
            this.type = SourceType.FULL;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        final int result = super.hashCode();
        return prime * result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FRSourceImpl other = (FRSourceImpl) obj;
        return Objects.equals(filter, other.filter) && type == other.type;
    }
}
