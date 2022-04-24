package model.utility;

/**
 * Class for a pair of two elements.
 *
 * @param <X> The type of the element x.
 * @param <Y> The type of the element y.
 */
public class Pair<X, Y> {
    private X x;
    private Y y;

    /**
     * Construct a pair from two values.
     * 
     * @param x X value
     * @param y Y value
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Set the field x.
     * 
     * @param x value
     */
    public void setX(final X x) {
        this.x = x;
    }

    /**
     * Set the field y.
     * 
     * @param y value
     */
    public void setY(final Y y) {
        this.y = y;
    }

    /**
     * Set both fields x and y.
     * 
     * @param x X value
     * @param y Y value
     */
    public void setValues(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for field X.
     * 
     * @return the value of X
     */
    public X getX() {
        return x;
    }

    /**
     * Getter for field Y.
     * 
     * @return the value of Y
     */
    public Y getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
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

        final Pair<?, ?> other = (Pair<?, ?>) obj;

        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
