package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.utility.PageLoader;

public class MainController implements Initializable {
    private static final String FXML_PATH = "src/main/resources/fxml/";
    @FXML private BorderPane listenerPane;
    @FXML private BorderPane sourcePane;
    @FXML private BorderPane equalizerPane;
    @FXML private BorderPane environmentPane;

    public MainController() {
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        setPane(listenerPane, FXML_PATH + "listenerView.fxml");
        setPane(sourcePane, FXML_PATH + "sourceView.fxml");
        setPane(equalizerPane, FXML_PATH + "equalizerView.fxml");
        setPane(environmentPane, FXML_PATH + "environmentView.fxml");
    }

    private void setPane(final BorderPane pane, final String path) {
        final Pane view = PageLoader.getPage(path);
        pane.setCenter(view);
    }
}
