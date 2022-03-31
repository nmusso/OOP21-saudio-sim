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
    public Source createFreqRangeSource(final SourceType type) {
        return new FreqRangeSourceImpl(type);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Source createFreqRangeSourceWithPos(final Vec3f position, final SourceType type) {
        return new FreqRangeSourceImpl(position, type);
    }
 
}
