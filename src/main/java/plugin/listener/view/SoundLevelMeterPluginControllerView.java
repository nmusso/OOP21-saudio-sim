package plugin.listener.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.view.ListenerView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.utility.Vec3f;
import plugin.listener.controller.SoundLevelMeterPluginController;

/**
 * 
 * Controller for SoundLevelMeterPluginView.
 *
 */
public class SoundLevelMeterPluginControllerView implements Initializable, SoundLevelMeterPluginView {
    @FXML private Tab soundLevelMeterPluginTab;
    @FXML private Circle circleShape;
    @FXML private Button btnStatus;
    private ListenerView listenerView;
    private SoundLevelMeterPluginController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final SoundLevelMeterPluginController c) {
        this.controller = c;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListenerControllerView(final ListenerView view) {
        this.listenerView = view;
        this.listenerView.setPlugin(this.soundLevelMeterPluginTab);

    }

    /**
     * {@inheritDoc}
     */
    public void setColor(final Vec3f rgb) {
        this.circleShape.setFill(Color.rgb((int) rgb.getX(), (int) rgb.getY(), (int) rgb.getZ()));
    }

    /**
    * Handle on mouse click on btnRemove to remove the plug-in.
     */
    @FXML private void handleBtnRemove() { //NOPMD: called by javaFX
        this.listenerView.removePlugin(this.soundLevelMeterPluginTab);
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
