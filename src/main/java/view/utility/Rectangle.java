package view.utility;

/**
 * Rectangles store position and size; used to check overlap between sprites.
 */
public class Rectangle {

    private double width;
    /**
    *
    */
    public double getWidth() {
        return width;
    }

    /**
    *
    */
    public void setWidth(final double width) {
        this.width = width;
    }

    /**
    *
    */
    public double getHeight() {
        return height;
    }

    /**
    *
    */
    public void setHeight(final double height) {
        this.height = height;
    }

    /**
    *
    */
    public Vector getPosition() {
        return position;
    }

    /**
    *
    */
    public void setPosition(final Vector position) {
        this.position = position;
    }

    private double height;
    private Vector position;

    /**
     * Rectangle Constructor: sets default values to a 1x1 rectangle, top-left
     * corner at (0,0).
     */
    public Rectangle() {
        width = 1;
        height = 1;
        position = new Vector(0, 0);
    }

    /**
     * Rectangle Constructor; sets position of top-left corner to (x,y) and the size
     * to w by h.
     *
     * @param x coordinate for left side
     * @param y coordinate for top side
     * @param w width
     * @param h height
     */
    public Rectangle(final double x, final double y, final double w, final  double h) {
        position = new Vector(x, y);
        width = w;
        height = h;
    }

    /**
     * Update the position of this rectangle.
     *
     * @param x new coordinate of left side
     * @param y new coordinate of top side
     */
    public void setPosition(final double x, final double y) {
        position.setValues(x, y);
    }

    /**
     * Set size of this rectangle.
     *
     * @param w new width of rectangle
     * @param h new height of rectangle
     */
    public void setSize(final double w, final double h) {
        width = w;
        height = h;
    }

    /**
     * Check if this rectangle overlaps another rectangle.
     *
     * @param other other rectangle to check for overlap
     * @return true if rectangles overlap
     */
    public boolean overlap(final Rectangle other) {
        boolean notOverlap = (position.getX() + width < other.position.getX()) || (position.getY() + height < other.position.getY())
                || (other.position.getX() + other.width < position.getX()) || (other.position.getY() + other.height < position.getY());

        return !(notOverlap);
    }

}
