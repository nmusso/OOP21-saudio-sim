package model.utility;

import java.util.Objects;

public class Vec3f {

    private float x;
    private float y;
    private float z;

    public Vec3f(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * @param x
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Sets the y of the position.
     * 
     * @param y
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * Sets the z of the position.
     * 
     * @param z
     */
    public void setZ(final float z) {
        this.z = z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

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
        Vec3f other = (Vec3f) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
                && Float.floatToIntBits(y) == Float.floatToIntBits(other.y)
                && Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
    }


}
