package view.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import controller.MainController;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.utility.Pair;
import view.utility.PageLoader;

/**
 * 
 * Controller of the view MainView.
 *
 */
public class MainControllerView implements Initializable, ControllerView {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        container.setPrefSize(contWidth, contHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrMain = ctrMain;
        this.ctrMain.setControllerView(this);

        this.initializePanes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String error) {
        final Alert alert = new Alert(AlertType.INFORMATION, error, ButtonType.OK);
        alert.show();
    }

    /**
     * Set all the panes in the view.
     */
    private void initializePanes() {
        setPane(listenerPane, FXML_PATH + "listenerView.fxml");
        setPane(sourcePane, FXML_PATH + "sourceView.fxml");
        setPane(songPane, FXML_PATH + "songView.fxml");
        setPane(equalizerPane, FXML_PATH + "equalizerView.fxml");
        setPane(spaceConfigPane, FXML_PATH + "spaceConfig.fxml");
        setPane(environmentPane, FXML_PATH + "environmentView.fxml");
    }

    /**
     * Set the selected pane imported from the path.
     * @param pane the pane
     * @param path the path of the pane
     */
    private void setPane(final BorderPane pane, final String path) {
        final Optional<Pair<Pane, ControllerView>> infoElm = PageLoader.getPage(path);

        infoElm.ifPresent(x -> {
            pane.setCenter(x.getX());
            x.getY().setControllerApplication(this.ctrMain);
        });
    }
}
