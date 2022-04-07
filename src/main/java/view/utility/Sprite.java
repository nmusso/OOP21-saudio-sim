package view.utility;

import javafx.scene.canvas.*;

/**
 * Represents the game entities displayed on the screen; can move and interact
 * with each other.
 */
public class Sprite {
    private Vector position;
    private Vector velocity;
    private Rectangle size;
    private Texture texture;
    private boolean visible;

    /**
     * Sprite Constructor; initializes to position (0,0).
     *
     */
    public Sprite() {
        position = new Vector(0, 0);
        velocity = new Vector(0, 0);
        size = new Rectangle();
        // make rectangle share same position data as this sprite
        size.setPosition(position);
        // by default, all sprites are visible
        visible = true;
    }

    /**
     * Sprite Constructor; initializes position to (x,y).
     *
     * @param x x coordinate of top-left corner
     * @param y y coordinate of top-left corner
     */
    public Sprite(final double x, final double y) {
        position = new Vector(x, y);
        size = new Rectangle();
        // make rectangle share same position data as this sprite
        size.setPosition(position);
        // by default, all sprites are visible
        visible = true;
    }

    /**
     * Update the position of this sprite. Automatically updates position of
     * rectangle bounds.
     *
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void setPosition(final double x, final double y) {
        position.setValues(x, y);
    }

    /**
     * Update the size of this sprite. Used for collision detection. By default,
     * size of sprite is size of texture image region.
     *
     * @param width  size along x direction
     * @param height size along y direction
     */
    public void setSize(final double width, final double height) {
        size.setSize(width, height);
    }

    /**
     * Set the texture data used for drawing this sprite in the game.
     *
     * @param tex previously created texture object, containing an image
     */
    public void setTexture(final Texture tex) {
        texture = tex;
        // by default, set rectangle size to image size
        size.setSize(tex.getRegion().getWidth(), tex.getRegion().getHeight());
    }

    /**
     * Set visibility of this sprite, which determines whether it appears on the
     * screen.
     *
     * @param vis should this sprite be visible?
     */
    public void setVisible(final boolean vis) {
        visible = vis;
    }

    /**
     * Draw the image contained in this sprite, at the position stored in this
     * sprite, with the size stored in the rectangle in this sprite.
     *
     * @param context The context object attached to the canvas in the window where
     *                the game will appear.
     */
    public void draw(final  GraphicsContext context) {
        if (visible) {
            context.drawImage(texture.getImage(), position.getX(), position.getY(), size.getWidth(), size.getHeight());
        }
    }

    /**
     * Move the sprite's position (x, y) by adding the given amounts.
     *
     * @param xAmount amount to add to x-coordinate of position
     * @param yAmount amount to add to y-coordinate of position
     */
    public void moveBy(final double xAmount, final double yAmount) {
        position.addValues(xAmount, yAmount);
    }

    /**
     * Check if this sprite overlaps another sprite by checking if this sprite's
     * rectangle overlaps other sprite's rectangle.
     *
     * @param other the other sprite to check for overlap with
     * @return true, if sprites overlap
     */
    public boolean overlap(final Sprite other) {
        return this.size.overlap(other.size);
    }

}
