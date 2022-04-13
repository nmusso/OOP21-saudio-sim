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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class ListenerControllerView implements Initializable, ControllerView {
    @FXML private Button btn;
    @FXML private TabPane listenerPane;
    @FXML private MenuItem it;
    @FXML private ComboBox<String> comboBoxPlugin;
    @FXML private Label lblXPos;
    @FXML private Label lblYPos;
    private final ObservableList<String> pluginItems = FXCollections.observableArrayList();
    private ListenerController ctrListener;


    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.comboBoxPlugin.setItems(pluginItems);
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
        aviablePlugin.forEach(x -> this.pluginItems.add(x));
    }

    /**
     * @param tab
     */
    public void setTabPlugin(final Tab tab) {
        this.listenerPane.getTabs().add(tab);
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
    public void positionChanged() {
        this.lblXPos.setText(Float.toString(this.ctrListener.getListener().getPosition().getX()));
        this.lblYPos.setText(Float.toString(this.ctrListener.getListener().getPosition().getY()));
    }

}
