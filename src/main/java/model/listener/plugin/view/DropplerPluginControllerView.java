package model.listener.plugin.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.view.ListenerControllerView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import model.listener.plugin.controller.DropplerPluginController;

public class DropplerPluginControllerView implements Initializable, ControllerPluginView<DropplerPluginController>  {
    @FXML private Tab pluginTab;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setControllerApplication(final DropplerPluginController c) { }



    /**
     * 
     * @param c
     */
    @Override
    public void setListenerControllerView(final ListenerControllerView c) {
        c.setTabPlugin(this.pluginTab);
    }
}
