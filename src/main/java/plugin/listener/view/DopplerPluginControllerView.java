package plugin.listener.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.view.ListenerControllerView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import model.listener.plugin.ControllerPluginView;
import plugin.listener.controller.DopplerPluginController;

public class DopplerPluginControllerView implements Initializable, ControllerPluginView<DopplerPluginController>  {
    @FXML private Tab dropplerPluginTab;
    @FXML private Label lblVelX;
    @FXML private Label lblVelY;
    private DopplerPluginController controller;

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
     * @param c
     */
    @Override
    public void setListenerControllerView(final ListenerControllerView c) {
        c.setTabPlugin(this.dropplerPluginTab);
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

}
