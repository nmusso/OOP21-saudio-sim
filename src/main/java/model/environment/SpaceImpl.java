package model.environment;

import model.extension.Extension;

public class SpaceImpl implements Space {
    private Extension filter;
    private float maxLenght;
    private float maxWidth;

    public SpaceImpl(final float maxLenght, final float maxWidth) {
        super();
        this.maxLenght = maxLenght;
        this.maxWidth = maxWidth;
    }

    public SpaceImpl(final float maxLenght, final float maxWidth, final Extension filter) {
        super();
        this.filter = filter;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Extension getFilterEffect() {
        return filter;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public void setFilterEffect(final Extension newFilter) {
        this.filter = newFilter;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getLenght() {
        return this.maxLenght;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getWidth() {
        return this.maxWidth;
    }

}
