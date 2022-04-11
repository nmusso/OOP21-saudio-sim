package controller.view;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import controller.EnvironmentController;
import controller.MainController;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.audiomanager.AudioManager;
import model.source.Source;
import model.source.SourceImpl;
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

    private Sprite lastSelectedSource;
    private Sprite listener;

    private Set<Sprite> sprites;

    //proporzioni TODO settarle da space controller
    private double lenght = 10;
    private double width = 10;

    //TODO temporaneee da controllare
    private double x;
    private double y;

    /**
     * 
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        sprites = new HashSet<>();
        x = canvas.getHeight();
        y = canvas.getWidth();

        contextView = canvas.getGraphicsContext2D();

        conteinerCanvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.doubleValue());
            sprites.forEach(e -> {
                e.setPosition((e.getPosition().getX() * newVal.doubleValue()) / x, e.getPosition().getY());
            });
            x = newVal.doubleValue();
        });

        conteinerCanvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setHeight(newVal.doubleValue());
            sprites.forEach(e -> {
                e.setPosition(e.getPosition().getX(), (e.getPosition().getY() * newVal.doubleValue()) / y);
            });
            y = newVal.doubleValue();
        });

        //TODO AGGIUNGI LISTENER
        addSprite(TypeSprite.LISTENER, -1, new Vec3f(0.0f));

        // ascolta il drag
        canvas.setOnMouseDragged(event -> {
            moveSprite(event);
        });

        AnimationTimer musicLoop = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                sprites.stream().forEach(e -> {
                    e.setVelocity(new Vector(0, 0));
                    e.getVelocity().multiply(1 / 60.0);
                });
                contextView.setFill(Color.WHITE);
                contextView.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                sprites.stream().forEach(e -> {
                    e.draw(contextView);
                });
            }
        };

        musicLoop.start();
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
            temp.get().setPosition(newPos.getX(), newPos.getY());
            final Pair<Float, Float> posFloat = new Pair<Float, Float>((float) ((width * newPos.getX()) /  canvas.getWidth()), (float) ((lenght * newPos.getY()) / canvas.getHeight()));
            if (temp.get().getSpriteType() == TypeSprite.LISTENER) {
                listener = temp.get();
                this.ctrl.moveListener(new Vec3f(posFloat.getX(), posFloat.getY(), 0.0f));
            } else {
                lastSelectedSource = temp.get();
                this.ctrl.moveSource(new Vec3f(posFloat.getX(), posFloat.getY(), 0.0f), lastSelectedSource.getId());
            }
            //TODO Non puo uscire dalla canvas
        }
    }

    /**
     * 
     */
    public void addSprite(/* type */ final TypeSprite type, final int id, final Vec3f pos) {
        final Sprite sprite = new Sprite(id);
        sprite.setSpriteType(type);

        sprite.setPosition((double) pos.getX(), (double) pos.getY());

        final Texture tx = new Texture(type.toString());
        sprite.setTexture(tx);
        sprite.draw(contextView);
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
     */
    public double getLenght() {
        return lenght;
    }

    /**
     * 
     */
    public void setLenght(final double lenght) {
        this.lenght = lenght;
    }

    /**
     * 
     * 
     */
    public double getWidth() {
        return width;
    }

    /**
     * 
     */
    public void setWidth(final double width) {
        this.width = width;
    }

    /**
     * 
     * 
     */
    public int getLastSelectedSource() {
        System.out.println(lastSelectedSource.getSpriteType());
        return lastSelectedSource.getId();
    }
    /**
     * 
     */
    public void removeSpriteSource() {
        sprites.remove(lastSelectedSource);
    }

    /**
     * 
     * @param type
     * @param id
     */
    public void upgradeTypeSpriteSource(final TypeSprite type) {
        lastSelectedSource.setSpriteType(type);
    }
 
}
