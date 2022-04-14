package controller.view;

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
import view.utility.Rectangle;
import view.utility.Sprite;
import view.utility.Texture;
import view.utility.TypeSprite;
import view.utility.Vector;

public class EnvironmentControllerView implements Initializable, ControllerView {

    private EnvironmentController ctrl;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane conteinerCanvas;
    @FXML
    private GraphicsContext contextView;

    private Optional<Sprite> lastSelectedSource;

    private Color colorFill = Color.LIGHTGRAY;

    private Set<Sprite> sprites;

    private double lenght = 10;
    private double width = 10;

    /**
     * 
     * 
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

        AnimationTimer musicLoop = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                sprites.stream().forEach(e -> {
                    e.setVelocity(new Vector(0, 0));
                    e.getVelocity().multiply(1 / 60.0);
                });
                contextView.setFill(colorFill);
                contextView.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                sprites.stream().forEach(e -> {
                    Pair<Double, Double> pos = checkOutOfBorder(
                            new Pair<Double, Double>(e.getPosition().getX(), e.getPosition().getY()), e.getSize());
                    e.setPosition(pos.getX(), pos.getY());
                    e.draw(contextView);
                });
            }
        };
        musicLoop.start();
    }


    @FXML public final void handleOnMouseClickedOrDrag(final Event event) {
        moveSprite(event);
    }

    /**
     * 
     */
    private void moveSprite(final Event event) {
        final Optional<Sprite> temp = sprites.stream()
                .filter(s -> s.getSize()
                        .overlap(new Rectangle(((MouseEvent) event).getX(), ((MouseEvent) event).getY(), 0.0, 0.0)))
                .findAny();
        if (temp.isPresent()) {
            Pair<Double, Double> newPos = new Pair<>(
                    ((MouseEvent) event).getX() - (temp.get().getSize().getWidth() / 2),
                    ((MouseEvent) event).getY() - (temp.get().getSize().getHeight() / 2));
            // CheckOutOfBorder
            newPos = checkOutOfBorder(newPos, temp.get().getSize());
            temp.get().setPosition(newPos.getX(), newPos.getY());
            final Pair<Float, Float> posFloat = updatePosForController(newPos);
            if (temp.get().getSpriteType() == TypeSprite.LISTENER) {
                this.ctrl.moveListener(new Vec3f(posFloat.getX(), posFloat.getY(), 0.0f));
            } else {
                lastSelectedSource = Optional.of(temp.get());
                this.ctrl.lastSelectedSourceChange();
                this.ctrl.moveSource(new Vec3f(posFloat.getX(), posFloat.getY(), 0.0f), lastSelectedSource.get().getId());
            }
        } else {
            lastSelectedSource = Optional.empty();
            this.ctrl.lastSelectedSourceChange();
        }
    }

    private Pair<Float, Float> updatePosForController(final Pair<Double, Double> pos) {
        final Pair<Float, Float> posFloat = new Pair<Float, Float>(
                (float) ((width * pos.getX()) / canvas.getWidth()),
                (float) ((lenght * pos.getY()) / canvas.getHeight()));
        return posFloat;
    }

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
     * 
     */
    public void addSprite(/* type */ final TypeSprite type, final int id, final Vec3f posElement) {
        final Sprite sprite = new Sprite(id);
        sprite.setSpriteType(type);
        final Texture tx = new Texture(type.toString());
        sprite.setTexture(tx);
        Pair<Double, Double> posDouble = new Pair<Double, Double>(
                (double) ((canvas.getWidth() * posElement.getX()) / width),
                (double) ((canvas.getHeight() * posElement.getY()) / lenght));
        posDouble = checkOutOfBorder(new Pair<Double, Double>(posDouble.getX(), posDouble.getY()), sprite.getSize());
        sprite.setPosition((double) posDouble.getX(), (double) posDouble.getY());
        sprite.draw(contextView);
        //aggiungere il controllo se e una sorce e fare il alert per sciro
        //aggiungere il fatto che potreti mettero in un metodo privato
        sprites.add(sprite);
    }

    /**
     * 
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrl = ctrMain.getEnvironmentController();
        this.ctrl.setControllerView(this);
    }

   /**
    * 
    * @return TODO
    */
    public double getLenght() {
        return lenght;
    }

    /**
     * 
     * @return TODO
     */
    public double getWidth() {
        return width;
    }

    /**
     * 
     * @return TODO
     */
    public int getLastSelectedSource() {
        return lastSelectedSource.isPresent()? lastSelectedSource.get().getId() : -1;
    }

    /**
     * 
     */
    public void removeSpriteSource() {
        sprites.remove(lastSelectedSource.get());
    }

    /**
     * 
     * @param type
     */
    public void upgradeTypeSpriteSource(final TypeSprite type) {
        lastSelectedSource.get().setSpriteType(type);
    }

//    public void changePreset(FileInputStream file) {
//        Image img = new Image(file);
//        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        Background bGround = new Background(bImg);
//        conteinerCanvas.setBackground(bGround);
//        System.out.println("do");
//        colorFill = Color.TRANSPARENT;
//
//    }

    /**
     * 
     * @param lenght
     * @param width
     */
    public void setSize(final double lenght, final double width) {
        this.lenght = lenght;
        this.width = width;
        sprites.stream().forEach(e -> {
            Pair<Float, Float> x = updatePosForController(new Pair<Double, Double>(e.getPosition().getX(), e.getPosition().getY()));
            Vec3f vec = new Vec3f(x.getX(), x.getY(), 0f);
            if (e.getSpriteType() == TypeSprite.LISTENER) {
                this.ctrl.moveListener(vec);
            } else {
                this.ctrl.moveSource(vec, e.getId());
                this.ctrl.lastSelectedSourceChange();
            }
        });
    }

    @Override
    public void showError(final String error) {
        // TODO Auto-generated method stub
    }

    /**
     *  TODO.
     */
    public void reset() {
        sprites.clear();
    }

}
