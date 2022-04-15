package view.utility;

import javafx.scene.canvas.GraphicsContext;
import model.utility.Pair;

public class SpriteImpl implements Sprite {

    private final int id;
    private final Pair<Double, Double> position = new Pair<Double, Double>(0.0, 0.0);
    private TypeSprite spriteType;
    private Rectangle size;
    private Texture texture;
    private boolean visible;

    /**
    * {@inheritDoc}
    */
    public int getId() {
        return id;
    }

    /**
    * {@inheritDoc}
    */
    public Pair<Double, Double> getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    public void setPosition(final double x, final double y) {
        position.setValues(x, y);
    }

    /**
    * {@inheritDoc}
    */
    public TypeSprite getTypeSprite() {
        return spriteType;
    }

    /**
    * {@inheritDoc}
    */
    public void setTypeSprite(final TypeSprite type) {
        this.spriteType = type;
        this.setTexture(new TextureImpl(type.toString()));
    }

    /**
    * {@inheritDoc}
    */
    public Rectangle getSize() {
        return size;
    }

    /**
    * {@inheritDoc}
    */
    public void setSize(final Rectangle size) {
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    public void setSize(final double width, final double height) {
        size.setSize(width, height);
    }

    /**
    * {@inheritDoc}
    */
    public Texture getTexture() {
        return texture;
    }

    /**
     * {@inheritDoc}
     */
    public void setTexture(final Texture tex) {
        texture = tex;
        this.setSize(tex.getRegion().getWidth(), tex.getRegion().getHeight());
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(final boolean vis) {
        visible = vis;
    }

    /**
     * Sprite Constructor; initializes to position (0,0).
     * @param id
     */
    public SpriteImpl(final int id) {
        setSize(new RectangleImpl());
        this.id = id;
        // make rectangle share same position data as this sprite
        getSize().setPosition(position);
        visible = true;
    }

    /**
     * {@inheritDoc}
     */
    public void draw(final  GraphicsContext context) {
        if (visible) {
            context.drawImage(texture.getImage().getImage(), position.getX(), position.getY(), getSize().getWidth(), getSize().getHeight());
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean overlap(final Sprite other) {
        return this.getSize().overlap(other.getSize());
    }
}
