package view.controller;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import controller.EnvironmentController;
import controller.MainController;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.utility.Pair;
import model.utility.Vec3f;
import view.EnvironmentView;
import view.utility.Rectangle;
import view.utility.RectangleImpl;
import view.utility.Sprite;
import view.utility.SpriteImpl;
import view.utility.TextureImpl;
import view.utility.TypeSprite;

public class EnvironmentControllerView implements Initializable, ControllerView, EnvironmentView {

    private EnvironmentController ctrl;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane conteinerCanvas;
    @FXML
    private GraphicsContext contextView;

    private final Sprite backGround = new SpriteImpl(0);
    private TextureImpl txBackGround;
    private Boolean backGroundStatus = false;

    private Optional<Sprite> lastSelectedSource;

    private final Color colorFill = Color.LIGHTGRAY;

    private Set<Sprite> sprites;

    private double x = 10;
    private double y = 10;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        sprites = new HashSet<>();
        contextView = canvas.getGraphicsContext2D();

        conteinerCanvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.doubleValue() - 1);
        });

        conteinerCanvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setHeight(newVal.doubleValue() - 1);
        });

        final AnimationTimer musicLoop = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                contextView.setFill(colorFill);
                contextView.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());


                if (backGroundStatus) {
                    txBackGround.setImageViewSize(canvas.getWidth(), canvas.getHeight());
                    backGround.setTexture(txBackGround);
                    backGround.draw(contextView);
                }

                sprites.stream().forEach(e -> {
                    final Pair<Double, Double> pos = checkOutOfBorder(
                            new Pair<Double, Double>(e.getPosition().getX(), e.getPosition().getY()), e.getSize());
                    e.setPosition(pos.getX(), pos.getY());
                    e.draw(contextView);
                });
            }
        };
        musicLoop.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrl = ctrMain.getEnvironmentController();
        this.ctrl.setControllerView(this);
    }


    /**
     * Handle on mouse click or drag on canvas, for check and move sprites.
     * @param event the event who triggered the method
     */
    @FXML 
    private void handleOnMouseClickedOrDrag(final Event event) { // NOPMD: Called by JavaFX
        final Optional<Sprite> temp = sprites.stream()
                .filter(s -> s.getSize()
                        .overlap(new RectangleImpl(((MouseEvent) event).getX(), ((MouseEvent) event).getY(), 0.0, 0.0)))
                .findAny();

        if (temp.isPresent()) {
            Pair<Double, Double> newPos = new Pair<>(
                    ((MouseEvent) event).getX() - (temp.get().getSize().getWidth() / 2),
                    ((MouseEvent) event).getY() - (temp.get().getSize().getHeight() / 2));
            // CheckOutOfBorder
            newPos = checkOutOfBorder(newPos, temp.get().getSize());
            temp.get().setPosition(newPos.getX(), newPos.getY());
            final Pair<Float, Float> posFloat = updatePosForController(newPos);
            if (temp.get().getTypeSprite().equals(TypeSprite.LISTENER)) {
                this.ctrl.moveListener(new Vec3f(posFloat.getX(), posFloat.getY(), 0.0f));
            } else {
                this.ctrl.moveSource(new Vec3f(posFloat.getX(), posFloat.getY(), 0.0f), temp.get().getId());
                setLastSelectedSource(temp.get());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTxBackGround(final String back) {
        if (!"void".equals(back)) {
            txBackGround = new TextureImpl(back);
            backGround.setTexture(txBackGround);
            backGroundStatus = true;
        } else {
            backGroundStatus = false;
        }
    }

    /**
     * returns the position of the source or listener(float) for the controller in proportion to the position of the sprites (double).
     * @param pos double 
     * @return Pair<Float, Float> for te controller
     */
    private Pair<Float, Float> updatePosForController(final Pair<Double, Double> pos) {
        return new Pair<Float, Float>(
                (float) ((x * pos.getX()) / canvas.getWidth()),
                (float) ((y * pos.getY()) / canvas.getHeight()));
    }

    /**
     * Check the position is not outOfBorder in case block the movement.
     * @param newPos to check
     * @param size of sprite
     * @return pos controlled
     */
    private Pair<Double, Double> checkOutOfBorder(final Pair<Double, Double> newPos, final Rectangle size) {
        if (newPos.getX() > canvas.getWidth() - size.getWidth()) {
            newPos.setX(canvas.getWidth() - size.getHeight());
        }
        if (newPos.getY() > canvas.getHeight() - size.getHeight()) {
            newPos.setY(canvas.getHeight() - size.getHeight());
        }
        if (newPos.getX() < 0) {
            newPos.setX(0.0);
        }
        if (newPos.getY() < 0) {
            newPos.setY(0.0);
        }
        return newPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSprite(final TypeSprite type, final int id, final Vec3f posElement) {
        final SpriteImpl sprite = new SpriteImpl(id);
        sprite.setTypeSprite(type);
        final TextureImpl tx = new TextureImpl(type.toString());
        sprite.setTexture(tx);
        Pair<Double, Double> posDouble = new Pair<>(
                (double) ((canvas.getWidth() * posElement.getX()) / x),
                (double) ((canvas.getHeight() * posElement.getY()) / y));
        posDouble = checkOutOfBorder(new Pair<Double, Double>(posDouble.getX(), posDouble.getY()), sprite.getSize());
        sprite.setPosition((double) posDouble.getX(), (double) posDouble.getY());
        sprite.draw(contextView);
        checkSprite(sprite, posElement);
        sprites.add(sprite);
    }

    /**
     * check sprite to warn the controller of a change on the listener or sources.
     * @param sprite
     * @param posElement
     */
    private void checkSprite(final Sprite sprite, final Vec3f posElement) {
        if (sprite.getTypeSprite().equals(TypeSprite.LISTENER)) {
            this.ctrl.moveListener(new Vec3f(posElement.getX(), posElement.getY(), 0f));
        } else {
            setLastSelectedSource(sprite);
        }
    }

    /**
     * Set lastSecetedSource with new sourceSprite.
     * @param sprite
     */
    private void setLastSelectedSource(final Sprite sprite) {
        this.lastSelectedSource = Optional.ofNullable(sprite);
        this.ctrl.lastSelectedSourceChange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLastSelectedSource() {
        return lastSelectedSource.isPresent() ? lastSelectedSource.get().getId() : -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSpriteSource() {
        sprites.remove(lastSelectedSource.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeTypeSpriteSource(final TypeSprite type) {
        lastSelectedSource.get().setTypeSprite(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(final double x, final double y) {
        this.x = x;
        this.y = y;

        sprites.stream().forEach(e -> {
            final Pair<Float, Float> temp = updatePosForController(new Pair<Double, Double>(e.getPosition().getX(), e.getPosition().getY()));
            final Vec3f vec = new Vec3f(temp.getX(), temp.getY(), 0f);
            if (e.getTypeSprite().equals(TypeSprite.LISTENER)) {
                this.ctrl.moveListener(vec);
            } else {
                this.ctrl.moveSource(vec, e.getId());
                this.ctrl.lastSelectedSourceChange();
            }
        });
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        sprites.clear();
    }


    @Override
    public void showMessage(final String error) {

    }
}
