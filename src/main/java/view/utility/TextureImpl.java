package view.utility;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Implementation of Texture.
 */
public class TextureImpl implements Texture {
    private static final String IMG_PATH = "/img/";
    private Rectangle region;
    private final ImageView imageView = new ImageView();

    /**
     * {@inheritDoc}
     */
    public Rectangle getRegion() {
        return region;
    }

    /**
     * {@inheritDoc}
     */
    public void setRegion(final Rectangle region) {
        this.region = region;
    }

    /**
     * {@inheritDoc}
     */
    public ImageView getImage() {
        return this.imageView;
    }

    /**
     * {@inheritDoc}
     */
    public void setImageViewSize(final double x, final double y) {
        this.imageView.setFitWidth(x);
        this.imageView.setFitHeight(y);
        region = new RectangleImpl(0, 0,  imageView.getFitWidth(), imageView.getFitHeight());
    }

    /**
     * Create a texture; default region to draw is entire image.
     *
     * @param path name of the image file to load
     */
    public TextureImpl(final String path) {
        try (InputStream file = getClass().getResourceAsStream(IMG_PATH + path + ".png")) {
            final Image image = new Image(file);
            this.imageView.setImage(image);
            this.imageView.setFitWidth(image.getWidth());
            this.imageView.setFitHeight(image.getHeight());
            this.imageView.setPreserveRatio(true);
            region = new RectangleImpl(0, 0,  imageView.getFitWidth(), imageView.getFitHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
