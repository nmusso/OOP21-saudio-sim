package controller.view;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

import controller.MainController;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.utility.Pair;
import view.utility.PageLoader;

public class MainControllerView implements Initializable {
    private static final String FXML_PATH = "/fxml/";
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final double contHeight = screen.getHeight() * 0.75;
    private final double contWidth = contHeight * 1.75;
    @FXML private BorderPane listenerPane;
    @FXML private BorderPane sourcePane;
    @FXML private BorderPane equalizerPane;
    @FXML private BorderPane environmentPane;
    @FXML private BorderPane spaceConfigPane;
    @FXML private BorderPane songPane;
    @FXML private HBox container;
    private MainController ctrMain;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        container.setPrefSize(contWidth, contHeight);
    }

    /**
     * 
     * @param ctrMain
     */
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrMain = ctrMain;
        this.ctrMain.setControllerView(this);

        this.initializePanes();
    }

    /**
     * 
     * @param ctrMain
     */
    private void initializePanes() {
        setPane(listenerPane, FXML_PATH + "listenerView.fxml");
        setPane(sourcePane, FXML_PATH + "sourceView.fxml");
        setPane(songPane, FXML_PATH + "songView.fxml");
        setPane(equalizerPane, FXML_PATH + "equalizerView.fxml");
        setPane(spaceConfigPane, FXML_PATH + "spaceConfig.fxml");
        setPane(environmentPane, FXML_PATH + "environmentView.fxml");
    }

    private void setPane(final BorderPane pane, final String path) {
        final Optional<Pair<Pane, ControllerView>> infoElm = PageLoader.getPage(path);

        infoElm.ifPresent(x -> {
            pane.setCenter(x.getX());
            x.getY().setControllerApplication(this.ctrMain); /*TODO control if ctrMain is null*/
        });
    }
}
