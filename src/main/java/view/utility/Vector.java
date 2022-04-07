package view.utility;

/**
 * Store a two-dimensional vector (x, y).
 *
 * 
 * @version 1.0
 */
public class Vector {
    private double x;

    /**
    *
    */
    public double getX() {
        return x;
    }

    /**
    *
    */
    public void setX(final double x) {
        this.x = x;
    }

    /**
    *
    */
    public double getY() {
        return y;
    }

    /**
    *
    */
    public void setY(final double y) {
        this.y = y;
    }

    private double y;

    /**
     * Vector Constructor; initializes vector to (x, y) = (0, 0).
     *
     */
    public Vector() {
        x = 0;
        y = 0;
    }

    /**
     * Vector Constructor; initializes vector to (x, y) = (a, b).
     *
     * @param a initial x value
     * @param b initial y value
     */
    public Vector(final double a, final double b) {
        x = a;
        y = b;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Vector [x=" + x + ", y=" + y + "]";
    }

    /**
     * Calculate the length of this vector.
     *
     * @return the length of this vector
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Calculate the direction of this vector: the angle between the vector and the
     * x-axis.
     *
     * @return the angle (direction) of this vector in degrees
     */
    public double getAngle() {
        double angleRadians = Math.atan2(y, x);
        double angleDegrees = Math.toDegrees(angleRadians);
        return angleDegrees;
    }

    /**
     * Change the values stored in the vector.
     *
     * @param a the new x value
     * @param b the new y value
     */
    public void setValues(final double a, final double b) {
        x = a;
        y = b;
    }

    /**
     * Change the length of this vector, while keeping the current angle.
     *
     * @param newLength new length of this vector
     */
    public void setLength(final double newLength) {
        double angleDegrees = getAngle();
        double angleRadians = Math.toRadians(angleDegrees);
        x = newLength * Math.cos(angleRadians);
        y = newLength * Math.sin(angleRadians);
    }

    /**
     * Change the angle of this vector, while keeping the current length.
     *
     * @param newAngleDegrees new angle of this vector, in degrees
     */
    public void setAngle(final double newAngleDegrees) {
        double length = getLength();
        double newAngleRadians = Math.toRadians(newAngleDegrees);
        x = length * Math.cos(newAngleRadians);
        y = length * Math.sin(newAngleRadians);
    }

    /**
     * Add the values (a, b) to the current position (x, y).
     *
     * @param a the value to add to x
     * @param b the value to add to y
     */
    public void addValues(final double a, final double b) {
        x += a;
        y += b;
    }

    /**
     *
     */
    public void addValues(final Vector other) {
        this.addValues(other.x, other.y);
    }

    /**
     * 
     */
    public void multiply(final double m) {
        this.x *= m;
        this.y *= m;
    }

    /**
     * Check if this vector is equal to another vector: coordinates are within
     * 0.0000001 of each other.
     * 
     * @param other other vector to check for equality
     * @return true/false (whether vectors are equal)
     */
    public boolean equals(final Vector other) {
        // can't use this, because rounding errors
        // may cause unexpected results.
        // return (x == other.x && y == other.y);

        // instead, check how close the coordinates are.
        double dx = Math.abs(x - other.x);
        double dy = Math.abs(y - other.y);

        // equal means "very very close"
        return (dx < 0.0000001 && dy < 0.0000001);

    }

}
