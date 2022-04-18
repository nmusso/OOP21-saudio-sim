package plugin.listener.view;

import model.listener.plugin.ControllerPluginView;
import plugin.listener.controller.DopplerPluginController;

public interface DopplerPluginView extends ControllerPluginView<DopplerPluginController> {

    /**
     * 
     * @param x
     * @param y
     */
    void changeVelocity(float x, float y);
}
