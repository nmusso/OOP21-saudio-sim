package model.listener.plugin.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.view.ListenerControllerView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import model.listener.plugin.controller.DropplerPluginController;

public class DropplerPluginControllerView implements Initializable, ControllerPluginView<DropplerPluginController>  {
    @FXML private Tab dropplerPluginTab;
    @FXML private Label lblVelX;
    private DropplerPluginController controller;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     */
    @Override
    public void setControllerApplication(final DropplerPluginController controller) { 
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
        this.lblVelX.setText(Float.toString(x));
    }

}
