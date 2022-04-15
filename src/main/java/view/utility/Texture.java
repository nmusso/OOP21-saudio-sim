package view.utility;

import javafx.scene.image.ImageView;

/**
 * Stores an image and a rectangle to specify the region of the image to draw.
 */
public interface Texture {
    /**
     * Get the Rectangle region of Texture.
     * @return region Rectangle
     */
    Rectangle getRegion();

    /**
     * Set the Rectangle region of Texture.
     * @param region
     */
    void setRegion(Rectangle region);

    /**
     * get the image to draw.
     * @return ImageView of Texture
     */
    ImageView getImage();

    /**
     * Set the dimension of image.
     * @param x width
     * @param y height 
     */
    void setImageViewSize(double x, double y);
}
