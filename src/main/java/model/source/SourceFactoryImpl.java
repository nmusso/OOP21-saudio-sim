package model.source;

import model.utility.Vec3f;

public class SourceFactoryImpl implements SourceFactory {

    /**
    * @inheritDoc
    */
    @Override
    public Source createSource() {
        return new SourceImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Source createSourceWithPos(final Vec3f position) {
        return new SourceImpl(position);
    }

    /**
     * @inheritDoc
     */
    @Override
    public FRSource createFreqRangeSource() {
        return new FRSourceImpl(SourceType.FULL);
    }

    /**
    * @inheritDoc
    */
    @Override
    public FRSource createFreqRangeSource(final SourceType type) {
        return new FRSourceImpl(type);
    }

    /**
     * @inheritDoc
     */
    @Override
    public FRSource createFreqRangeSourceWithPos(final Vec3f position, final SourceType type) {
        return new FRSourceImpl(position, type);
    }

}
