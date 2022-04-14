package view.utility;


import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Stores an image and a rectangle to specify the region of the image to draw.
 */
public class Texture {
    private final Image image;
    private Rectangle region;
    private ImageView imageView = new ImageView();

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
//        this.imageView.setImage(image);
//        this.imageView.setPreserveRatio(true);
//        region = new Rectangle(0, 0,  imageView.getFitWidth(), imageView.getFitHeight());
        region = new Rectangle(0, 0,  image.getWidth(), image.getHeight());
    }

}
