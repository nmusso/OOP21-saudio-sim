package plugin.listener.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.view.ListenerView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import plugin.listener.controller.DopplerPluginController;

public class DopplerPluginControllerView implements Initializable, DopplerPluginView  {
    @FXML private Tab dropplerPluginTab;
    @FXML private Label lblVelX;
    @FXML private Label lblVelY;
    @FXML private Button btnStatus;
    private DopplerPluginController controller;
    private ListenerView listenerView;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     */
    @Override
    public void setControllerApplication(final DopplerPluginController controller) { 
        this.controller = controller;
    }



    /**
     * 
     * @param ctr
     */
    @Override
    public void setListenerControllerView(final ListenerView ctr) {
        this.listenerView = ctr;
        this.listenerView.setPlugin(this.dropplerPluginTab);
    }

    /**
     * 
     * @param x
     * @param y
     */
    public void changeVelocity(final float x, final float y) {
        this.lblVelX.setText(String.valueOf(Math.round(x * 100.0) / 100.0));
        this.lblVelY.setText(String.valueOf(Math.round(y * 100.0) / 100.0));
    }

    @FXML private void handleBtnRemove() { // NOPMD: called by javafx
        this.listenerView.removePlugin(this.dropplerPluginTab);
        this.controller.removePlugin();
    }

    @FXML private void handleBtnStatus() { // NOPMD: called by javafx
        this.btnStatus.setText(this.controller.getPlugin().isEnabled() ? "Enable" : "Disable");
        if (this.controller.getPlugin().isEnabled()) {
            this.controller.getPlugin().disable();
        } else {
            this.controller.getPlugin().enable();
        }
    }

}
