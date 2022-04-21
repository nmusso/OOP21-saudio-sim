package controller.view;


import java.net.URL;
import java.util.ResourceBundle;

import controller.ListenerController;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.utility.Vec3f;


public class ListenerControllerView implements Initializable, ListenerView {
    private static final int MIN_ANGLE_OR = -180;
    private static final int MAX_ANGLE_OR = 180;
    @FXML private Button btn;
    @FXML private TabPane listenerPane;
    @FXML private TabPane pluginPane;
    @FXML private Tab pluginTab;
    @FXML private MenuItem it;
    @FXML private ComboBox<String> comboBoxPlugin;
    @FXML private Label lblXPos;
    @FXML private Label lblYPos;
    @FXML private Slider sliderAtOr;
    @FXML private Label lblDegrees;
    private final ObservableList<String> pluginItems = FXCollections.observableArrayList();
    private ListenerController ctrListener;


    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.comboBoxPlugin.setItems(pluginItems);
        this.sliderAtOr.setMin(MIN_ANGLE_OR);
        this.sliderAtOr.setMax(MAX_ANGLE_OR);
        this.sliderAtOr.setValue(0);

        this.sliderAtOr.valueProperty().addListener((obs, oldV, newV) -> {
            this.ctrListener.getListener().setAtOrientation(new Vec3f((float) Math.sin(Math.toRadians(-newV.intValue())),
                                                                      (float) Math.cos(Math.toRadians(-newV.intValue())), 
                                                                       0.0f));
            this.lblDegrees.setText(String.valueOf(newV.intValue()));
        });

    }


    @FXML public final void handleAddPlugin(final Event event) {
        final String pluginName = this.comboBoxPlugin.getValue();

        if (!this.comboBoxPlugin.getSelectionModel().isEmpty()) {
            this.ctrListener.createPluginController(pluginName);
            this.pluginItems.remove(pluginName);
        }
    }

    @FXML public final void handleRefreshListPlugin() {
        final var aviablePlugin = this.ctrListener.getAvailablePlugin();
        this.pluginItems.clear();
        aviablePlugin.forEach(x -> this.pluginItems.add(x));
    }

    @FXML public final void handleEnableAll() {
        this.ctrListener.enableAll();
        this.pluginPane.getTabs().filtered(x -> !x.equals(this.pluginTab))
                                 .forEach(x -> x.setDisable(false));
    }

    @FXML public final void handleDisableAll() {
        this.ctrListener.disableAll();
        this.pluginPane.getTabs().filtered(x -> !x.equals(this.pluginTab))
                                 .forEach(x -> x.setDisable(true));
    }


    /**
     * @param tab
     */
    @Override
    public void setPlugin(final Tab tab) {
        this.pluginPane.getTabs().add(tab);
    }

    /**
     * 
     * @param tab
     */
    @Override
    public void removePlugin(final Tab tab) {
        this.pluginPane.getTabs().removeAll(tab);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrListener = ctrMain.getListenerCtr();
        this.ctrListener.setControllerView(this);
        this.handleRefreshListPlugin();
    }

    /**
     * 
     */
    @Override
    public void showError(final String error) {
        final var alert = new Alert(Alert.AlertType.WARNING, error);
        alert.show();
    }

    /**
     * 
     */
    @Override
    public void positionChanged() {
        this.lblXPos.setText(String.valueOf(Math.round(this.ctrListener.getListener().getPosition().getX() * 100.0) / 100.0));
        this.lblYPos.setText(String.valueOf(Math.round(this.ctrListener.getListener().getPosition().getY() * 100.0) / 100.0));
    }

}
