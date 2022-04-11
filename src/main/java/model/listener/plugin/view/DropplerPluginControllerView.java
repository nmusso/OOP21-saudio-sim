package model.listener.plugin.view;

import java.net.URL;
import java.util.ResourceBundle;

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



    @Override
    public void setListenerControllerView() {
        // TODO Auto-generated method stub
        //TODO add parameter
    }
}
