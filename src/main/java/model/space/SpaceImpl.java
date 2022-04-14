package model.space;

import java.util.Set;

import model.utility.Vec3f;

public class SpaceImpl implements Space {
    private final float yMax;
    private final float xMax;

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
    public boolean isAvailable(final Vec3f pos, final Set<Vec3f> positions) {
       return (checkBorder(pos) && checkBusyPos(pos, positions));
    }

    /**
    *check if the position is out of range.
    *@param vec3f pos to check
    * @return true if pos is ok
    */
    private boolean checkBorder(final Vec3f pos) {
        return ((pos.getX() > 0f || pos.getX() < this.xMax) && (pos.getY() > 0f || pos.getY() < this.yMax));
    }

    /**
    * check busy positions. 
    * @param vec3f pos to check
    * @return true if pos is free else false
    */
    private boolean checkBusyPos(final Vec3f pos, final Set<Vec3f> position) {
        return !position.contains(pos);
    }

}
