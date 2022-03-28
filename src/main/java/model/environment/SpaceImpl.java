package model.environment;

import model.filters.FilterEffects;

public class SpaceImpl implements Space {
    private FilterEffects filter;
    private float maxLenght;
    private float maxWidth;
    /**
    *
    * 
    *
    */
    public SpaceImpl(final float maxLenght, final float maxWidth) {
        super();
        this.maxLenght = maxLenght;
        this.maxWidth = maxWidth;
    }
    /**
    *
    * 
    *
    */
    public SpaceImpl(final float maxLenght, final float maxWidth, final FilterEffects filter) {
        super();
        this.filter = filter;
    }

    /**
    *
    * 
    *
    */
    @Override
    public FilterEffects getFilterEffects() {
        return filter;
    }
    /**
    *
    * 
    *
    */
    @Override
    public void setFilterEffects(final FilterEffects newFilter) {
        this.filter = newFilter;
    }
    /**
    *
    * 
    *
    */
    @Override
    public float getLenght() {
        return this.maxLenght;
    }
    /**
    *
    * 
    *
    */
    @Override
    public float getWidth() {
        return this.maxWidth;
    }

}
