package view.utility;

import javafx.scene.canvas.GraphicsContext;
import model.utility.Pair;

public interface Sprite {

    /**
     * Get ID of the sprite.
     * @return id
     */
    int getId();

    /**
     * Get the position of sprite.
     * @return Pair position
     */
     Pair<Double, Double> getPosition();
    /**
     * Set new pos with coordinates x and y.
     * @param x
     * @param y
     */
    void setPosition(double x, double y);

    /**
     * Get TypeSprite of Sprite. 
     * @return TypeSprite for img
     */
    TypeSprite getTypeSprite();
    /**
     * Set a new TypeSprite for backgroud of sprite.
     * @param type for set
     */
    void setTypeSprite(TypeSprite type);

    /**
     * Get Size of Sprite.
     * @return Rectangle of sprite.
     */
    Rectangle getSize();
    /**
     * Set a new Rectangle.
     * @param size
     */
    void setSize(Rectangle size);
    /**
     * Set a new size in case of new Texture.
     * @param width
     * @param height
     */
    void setSize(double width, double height);


    /**
     * Get Texture of sprite (img and size).
     * @return Texture
     */
    Texture getTexture();
    /**
     * 
     * @param tex
     */
    void setTexture(Texture tex);

    /**
     * Boolean true if sprite is visible.
     * @return boolean
     */
    boolean isVisible();
    /**
     * Set the visibility of sprite.
     * @param vis boolean
     */
    void setVisible(boolean vis);

    /**
     * Draw the image contained in this sprite, at the position stored in this
     * sprite, with the size stored in the rectangle in this sprite.
     * 
     * @param context The context object attached to the canvas in the window where
     *                the game will appear.
     */
    void draw(GraphicsContext context);

    /**

     * Check if this sprite overlaps another sprite by checking if this sprite's
     * rectangle overlaps other sprite's rectangle.
     * @param other the other sprite to check for overlap with
     * @return true, if sprite overlap
     */
    boolean overlap(Sprite other);
}
