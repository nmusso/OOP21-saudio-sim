package view.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;


/**
 * Stores an image and a rectangle to specify the region of the image to draw.
 */
public class Texture {
    private final Image image;
    private Rectangle region;

    /**
     *
     */
    public Rectangle getRegion() {
        return region;
    }

    /**
    *
    */
    public void setRegion(final Rectangle region) {
        this.region = region;
    }

    /**
    *
    */
    public Image getImage() {
        return image;
    }

    /**
     * Create a texture; default region to draw is entire image.
     *
     * @param path name of the image file to load
     */
    public Texture(final String path) {
        final InputStream file = getClass().getResourceAsStream("/img/" + path + ".png");
        this.image = new Image(file);
        region = new Rectangle(0, 0,  image.getWidth(), image.getHeight());
    }

}
