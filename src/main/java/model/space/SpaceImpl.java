package model.space;

import java.util.List;

import model.utility.Vec3f;

/**
 * Implementation of Space.
 *
 */
public class SpaceImpl implements Space {
    private float yMax;
    private float xMax;

    /**
     * Construct a new SpaceImpl.
     * @param x for xMax
     * @param y for yMax
     */
    public SpaceImpl(final float x, final float y) {
        super();
        this.xMax = x;
        this.yMax = y;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getYmax() {
        return this.yMax;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getXmax() {
        return this.xMax;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public void setYMax(final float y) {
        this.yMax = y;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public void setXMax(final float x) {
        this.xMax = x;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public boolean isAvailable(final Vec3f pos, final List<Vec3f> positions) {
       return checkBorder(pos) && checkBusyPos(pos, positions);
    }

    /**
    *check if the position is out of borderMax.
    *@param vec3f position to check
    * @return true if position is okay
    */
    private boolean checkBorder(final Vec3f pos) {
        return pos.getX() <= this.xMax && pos.getX() >= 0f && pos.getY() >= 0f && pos.getY() <= this.yMax;
    }

    /**
    * check busy positions.
    * @param vec3f position to check
    * @return true if position is free else false
    */
    private boolean checkBusyPos(final Vec3f pos, final List<Vec3f> position) {
        return !position.contains(pos);
    }
}
