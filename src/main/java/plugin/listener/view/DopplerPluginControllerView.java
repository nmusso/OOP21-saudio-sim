package plugin.listener.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import plugin.listener.controller.DopplerPluginController;
import view.ListenerView;

/**
 * 
 * Controller for DopplerPluginView.
 *
 */
public class DopplerPluginControllerView implements Initializable, DopplerPluginView  {
    @FXML private Tab dropplerPluginTab;
    @FXML private Label lblVelX;
    @FXML private Label lblVelY;
    @FXML private Button btnStatus;
    private DopplerPluginController controller;
    private ListenerView listenerView;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final var alert = new Alert(Alert.AlertType.WARNING, "Use this Plugin only with a single source. Use with multiple sources may cause a delay in some sources. ");
        alert.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final DopplerPluginController controller) { 
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListenerControllerView(final ListenerView ctr) {
        this.listenerView = ctr;
        this.listenerView.setPlugin(this.dropplerPluginTab);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeVelocity(final float x, final float y) {
        this.lblVelX.setText(String.valueOf(Math.round(x * 100.0) / 100.0));
        this.lblVelY.setText(String.valueOf(Math.round(y * 100.0) / 100.0));
    }

    /**
     * Handle on mouse click on btnRemove to remove the plug-in.
     */
    @FXML private void handleBtnRemove() { //NOPMD: called by javaFX
        this.listenerView.removePlugin(this.dropplerPluginTab);
        this.controller.removePlugin();
    }

    /**
     * Handle on mouse click on btnStatus to enable or disable plug-in.
     */
    @FXML private void handleBtnStatus() { //NOPMD: called by javaFX
        this.btnStatus.setText(this.controller.getPlugin().isEnabled() ? "Enable" : "Disable");
        if (this.controller.getPlugin().isEnabled()) {
            this.controller.getPlugin().disable();
        } else {
            this.controller.getPlugin().enable();
        }
    }

}
