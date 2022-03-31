package model.environment;

import model.utility.Vec3f;

public class SpaceImpl implements Space {
    private final float scale;
    private final float maxLenght;
    private final float maxWidth;

    public SpaceImpl(final float maxLenght, final float maxWidth, final float scale) {
        super();
        this.maxLenght = maxLenght;
        this.maxWidth = maxWidth;
        this.scale = scale;
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

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getScale() {
        return this.scale;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public boolean isAvailable(final Vec3f pos) {
        if ((pos.getX() > 0f || pos.getX() < maxLenght) && (pos.getY() > 0f || pos.getY() < maxWidth)) {
            return true;
        }
        return false;
    }
}
