package view.utility;

import model.utility.Pair;

/**
 * Rectangles store position and size; used to check overlap between sprites.
 */
public class RectangleImpl implements Rectangle {

    private double width;
    private double height;
    private Pair<Double, Double> position;

    /**
     * {@inheritDoc}
     */
    public double getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    public void setWidth(final double width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    public double getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    public void setHeight(final double height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    public void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    public void setSize(final double w, final double h) {
        width = w;
        height = h;
    }


    /**
     * Rectangle Constructor: sets default values to a 1x1 rectangle, top-left
     * corner at (0,0).
     */
    public RectangleImpl() {
        width = 1;
        height = 1;
        position = new Pair<>(0.0, 0.0);
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
    public RectangleImpl(final double x, final double y, final double w, final  double h) {
        position = new Pair<>(x, y);
        width = w;
        height = h;
    }

    /**
     * {@inheritDoc}
     */
    public boolean overlap(final Rectangle other) {
        return !(position.getX() + width < other.getPosition().getX() || position.getY() + height < other.getPosition().getY()
                || other.getPosition().getX() + other.getWidth() < position.getX() || other.getPosition().getY() + other.getHeight() < position.getY());
    }
}
