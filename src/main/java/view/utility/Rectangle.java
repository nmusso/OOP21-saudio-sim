package view.utility;

import model.utility.Pair;

/**
 * Interface of Rectangles, used to check overlap between Sprites.
 */
public interface Rectangle {
    /**
     * Get the Width of Rectangle.
     * @return width
     */
    double getWidth();
    /**
     * Set width of rectangle.
     * @param width double x
     */
    void setWidth(double width);
    /**
     * Get the height of Rectangle.
     * @return height 
     */
    double getHeight(); 
    /**
     * Set height of rectangle.
     * @param height double y
     */
    void setHeight(double height);
    /**
     * Set position of rectangle.
     * @param position to occupy
     */
    void setPosition(Pair<Double, Double> position);
    /**
     * Get the position of rectangle.
     * @return position
     */
    Pair<Double, Double> getPosition();
    /**
     * Set size of rectangle.
     *
     * @param w new width of rectangle
     * @param h new height of rectangle
     */
    void setSize(double w, double h);

    /**
     * Check if this rectangle overlaps another rectangle.
     *
     * @param other other rectangle to check for overlap
     * @return true if rectangles overlap
     */
    boolean overlap(Rectangle other);
}
