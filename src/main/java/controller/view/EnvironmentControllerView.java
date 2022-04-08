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
import view.utility.Rectangle;
import view.utility.Sprite;
import view.utility.Texture;
import view.utility.TypeSprite;
import view.utility.Vector;

public class EnvironmentControllerView implements Initializable, ControllerView {
    private EnvironmentControllerView remote;
    @FXML
    private Canvas canvas;
    private AnchorPane conteinerCanvas;
    private GraphicsContext contextView;
    private Sprite lastSelect;

    private Set<Sprite> sprites;

    /**
     * 
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        remote = new EnvironmentControllerView();
        sprites = new HashSet<>();

        System.out.println();

        contextView = canvas.getGraphicsContext2D();

        //TODO Metterci lo spriteType
        addSprite(TypeSprite.SOURCEFULL);
        addSprite(TypeSprite.SOURCEMID);
        addSprite(TypeSprite.LISTENER);

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
        final Optional<Sprite> temp = sprites.stream().filter(s -> s.getSize().overlap(new Rectangle(((MouseEvent) event).getX(), ((MouseEvent) event).getY(), 0.0, 0.0))).findFirst();
        if (temp.isPresent()) {
            temp.get().setPosition(((MouseEvent) event).getX() - (temp.get().getSize().getWidth() / 2),
                    ((MouseEvent) event).getY() - (temp.get().getSize().getHeight() / 2));
            System.out.println(temp.get().getSpriteType().toString());
            lastSelect = temp.get();
         }
    }

    @Override
    public void setControllerApplication(final MainController ctrMain) {
        // TODO Auto-generated method stub
    }

    /**
     * 
     * TODO AGGIUNGERE IL TIPO ALLA SPRITE.
     */
    public void addSprite(/*type*/ final TypeSprite type) {
        final Sprite sprite = new Sprite();
        sprite.setSpriteType(type);
        sprite.setPosition(canvas.getHeight() / 2, canvas.getWidth() / 2);
        final Texture tx = new Texture(type.toString() + ".png");
        sprite.setTexture(tx);
        sprite.draw(contextView);
        sprites.add(sprite);
    }
}
