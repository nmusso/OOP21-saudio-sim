package controller.view;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import controller.MainController;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.environment.Environment;
import model.utility.Pair;
import view.utility.PageLoader;

public class MainControllerView implements Initializable {
    private static final double PROP = 0.45;
    private static final String FXML_PATH = "src/main/resources/fxml/";
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final double contHeight = screen.getHeight() * 0.75;
    private final double contWidth = contHeight * 1.75;
    @FXML private BorderPane listenerPane;
    @FXML private BorderPane sourcePane;
    @FXML private BorderPane equalizerPane;
    @FXML private BorderPane environmentPane;
    @FXML private BorderPane spaceConfigPane;
    @FXML private VBox container;
    private MainController ctrMain;
//      METODO STATICO DI SALVATAGGIO
//    private static final Set<Environment> environments = new HashSet<>();
//
//    static Set<Environment> getEnviroments() {
//        return environments;
//    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        container.setPrefSize(contWidth, contHeight);
    }

    private void setPane(final BorderPane pane, final String path) {
        final Optional<Pair<Pane, ControllerView>> infoElm = PageLoader.getPage(path);
//      pane.setPrefSize(contWidth * PROP, contHeight * PROP);
//      infoElm.get().getX().setPrefSize(contWidth * PROP, contHeight * PROP);
        infoElm.ifPresent(x -> {
            pane.setCenter(x.getX());
            x.getY().setControllerApplication(this.ctrMain); /*TODO control if ctrMain is null*/
        });
    }

    /**
     * 
     * @param ctrMain
     */
    public void setControllerApp(final MainController ctrMain) {
        this.ctrMain = ctrMain;
        this.initializePanes();
    }

    /**
     * 
     * @param ctrMain
     */
    private void initializePanes() {
        setPane(listenerPane, FXML_PATH + "listenerView.fxml");
        setPane(sourcePane, FXML_PATH + "sourceView.fxml");
        setPane(equalizerPane, FXML_PATH + "equalizerView.fxml");
        setPane(spaceConfigPane, FXML_PATH + "spaceConfig.fxml");
        setPane(environmentPane, FXML_PATH + "environmentView.fxml");
    }
}
