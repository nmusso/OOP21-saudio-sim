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
    private final ImageView imageView = new ImageView();

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
     * @return TODO 
     */
    public ImageView getImage() {
        return this.imageView;
    }

    /**
     * 
     * @param x
     * @param y
     */
    public void setImageViewSize(final double x, final double y) {
        this.imageView.setFitWidth(x);
        this.imageView.setFitHeight(y);
        region = new Rectangle(0, 0,  imageView.getFitWidth(), imageView.getFitHeight());
    }

    /**
     * Create a texture; default region to draw is entire image.
     *
     * @param path name of the image file to load
     */
    public Texture(final String path) {
        final InputStream file = getClass().getResourceAsStream("/img/" + path + ".png");
        this.image = new Image(file);
        this.imageView.setImage(image);
        this.imageView.setFitWidth(image.getWidth());
        this.imageView.setFitHeight(image.getHeight());
        this.imageView.setPreserveRatio(true);
        region = new Rectangle(0, 0,  imageView.getFitWidth(), imageView.getFitHeight());
    }

}
