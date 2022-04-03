package model.environment;

import java.util.HashSet;
import java.util.Set;

import model.utility.Vec3f;

public class SpaceImpl implements Space {
    private final float scale;
    private final float maxLenght;
    private final float maxWidth;
    private final Set<Vec3f> posElements;

    public SpaceImpl(final float maxLenght, final float maxWidth, final float scale) {
        super();
        this.maxLenght = maxLenght;
        this.maxWidth = maxWidth;
        this.scale = scale;
        posElements = new HashSet<>();
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
       if (checkBorder(pos) && checkBusyPos(pos)) {
           posElements.add(pos);
           return true;
       }
       return false;
    }

    /**
    *
    * TODO .
    */
    private boolean checkBorder(final Vec3f pos) {
        if ((pos.getX() > 0f || pos.getX() < maxLenght) && (pos.getY() > 0f || pos.getY() < maxWidth)) {
            return true;
        }
        return false;
    }

    /**
    *
    * TODO .
    */
    private boolean checkBusyPos(final Vec3f pos) {
        if (posElements.contains(pos)) {
            return false;
        }
        return true;
    }

    /**
    *
    * {@inheritDoc}
    */
    @Override
    public void removeSourcePos(final Vec3f pos) {
        posElements.remove(pos);
    }
}
