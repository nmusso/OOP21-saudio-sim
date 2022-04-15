package view.utility;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TextureImpl implements Texture{
    private final Image image;
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
        final InputStream file = getClass().getResourceAsStream("/img/" + path + ".png");
        this.image = new Image(file);
        this.imageView.setImage(image);
        this.imageView.setFitWidth(image.getWidth());
        this.imageView.setFitHeight(image.getHeight());
        this.imageView.setPreserveRatio(true);
        region = new RectangleImpl(0, 0,  imageView.getFitWidth(), imageView.getFitHeight());
    }
}
