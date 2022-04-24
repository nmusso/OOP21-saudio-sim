package model.utility;

import java.util.Objects;

/**
 * Class for a three value vector.
 *
 */
public class Vec3f {

    private float x;
    private float y;
    private float z;

    /**
     * Construct a new Vec3f with the given coordinates.
     * @param x X value
     * @param y Y value
     * @param z Z value
     */
    public Vec3f(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Construct a new Vec3f with the given value.
     * @param val the value of the three coordinates
     */
    public Vec3f(final float val) {
        this.x = val;
        this.y = val;
        this.z = val;
    }

    /**
     * Returns the x of the position.
     * 
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y of the position.
     * 
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the z of the position.
     * 
     * @return z
     */
    public float getZ() {
        return z;
    }

    /**
     * Sets the x of the position.
     * 
     * @param x X value
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Sets the y of the position.
     * 
     * @param y Y value
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * Sets the z of the position.
     * 
     * @param z Z value
     */
    public void setZ(final float z) {
        this.z = z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vec3f other = (Vec3f) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
                && Float.floatToIntBits(y) == Float.floatToIntBits(other.y)
                && Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "x: " + x + "y: " + y + "z:" + z;
    }


}
