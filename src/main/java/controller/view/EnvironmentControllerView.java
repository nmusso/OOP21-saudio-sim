package controller.view;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import view.utility.Rectangle;
import view.utility.Sprite;
import view.utility.Texture;
import view.utility.TypeSprite;
import view.utility.Vector;

public class EnvironmentControllerView implements Initializable, ControllerView {
    private EnvironmentControllerView remote;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane conteinerCanvas;
    @FXML
    private GraphicsContext contextView;
    private Sprite lastSelect;

    private static Set<Sprite> sprites;

    /**
     * 
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        remote = new EnvironmentControllerView();
        sprites = new HashSet<>();

        conteinerCanvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.doubleValue());
        });

        conteinerCanvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setHeight(newVal.doubleValue());
        });

        contextView = canvas.getGraphicsContext2D();

        // TODO Metterci lo spriteType
        addSprite(TypeSprite.LISTENER);
//        addSprite(TypeSprite.SOURCEFULL);
//        addSprite(TypeSprite.SOURCEMID);

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

    private void moveSprite(final Event event) {
        final Optional<Sprite> temp = sprites.stream()
                .filter(s -> s.getSize()
                        .overlap(new Rectangle(((MouseEvent) event).getX(), ((MouseEvent) event).getY(), 0.0, 0.0)))
                .findFirst();
        if (temp.isPresent()) {
            Pair<Double, Double> newPos = new Pair<>(
                    ((MouseEvent) event).getX() - (temp.get().getSize().getWidth() / 2),
                    ((MouseEvent) event).getY() - (temp.get().getSize().getHeight() / 2));
            final Optional<Sprite> test = sprites.stream().filter(s -> s.overlap(temp.get())).findAny();
            //TODO cambia questo if
            if (!test.isPresent() || test.get() == temp.get()) {
                temp.get().setPosition(newPos.getX(), newPos.getY());
                lastSelect = temp.get();
            } else {
                System.out.println("overlap");
                System.out.println(lastSelect.getSpriteType().toString());
            }
        }
    }

    /**
     * 
     * 
     */
    public void addSprite(/* type */ final TypeSprite type) {
        final Sprite sprite = new Sprite();
        sprite.setSpriteType(type);
        sprite.setPosition(canvas.getHeight() / 2, canvas.getWidth() / 2);
        final Texture tx = new Texture(type.toString());
        sprite.setTexture(tx);
        sprite.draw(contextView);
        sprites.add(sprite);
        lastSelect = sprite;
    }

    @Override
    public void setControllerApplication(final MainController ctrMain) {

    }
}
