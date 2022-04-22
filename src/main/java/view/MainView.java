package view;

import controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.controller.MainControllerView;

public class MainView extends Application {
    private static final String FXML_PATH = "/fxml/";
    private static final String CSS_PATH = "/css/";

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage mainStage) throws Exception {
        final MainController ctrMainApp = new MainController();
        mainStage.setTitle("Spatial Audio Simulator");

        final FXMLLoader loader = new FXMLLoader();
        final Parent root = loader.load(getClass().getResourceAsStream(FXML_PATH + "mainView.fxml"));
        final MainControllerView ctrMainV = loader.getController();
        ctrMainV.setControllerApplication(ctrMainApp);

        final Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(CSS_PATH + "style.css").toExternalForm());

        mainStage.setOnCloseRequest(x -> {
            ctrMainApp.closeAllThread();
            Platform.exit();
        });

        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();
        ctrMainApp.showIntroduction();
    }
}
