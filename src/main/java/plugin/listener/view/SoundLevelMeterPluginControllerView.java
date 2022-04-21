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

public class SoundLevelMeterPluginControllerView implements Initializable, SoundLevelMeterPluginView {
    @FXML private Tab soundLevelMeterPluginTab;
    @FXML private Circle circleShape;
    @FXML private Button btnStatus;
    private ListenerView listenerView;
    private SoundLevelMeterPluginController controller;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // TODO Auto-generated method stub

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

    @FXML public final void handleBtnRemove() {
        this.listenerView.removePlugin(this.soundLevelMeterPluginTab);
        this.controller.removePlugin();
    }

    @FXML public final void handleBtnStatus() {
        this.btnStatus.setText(this.controller.getPlugin().isEnabled() ? "Enable" : "Disable");
        if (this.controller.getPlugin().isEnabled()) {
            this.controller.getPlugin().disable();
        } else {
            this.controller.getPlugin().enable();
        }
    }


}
