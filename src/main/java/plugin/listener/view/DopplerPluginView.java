package plugin.listener.view;

import plugin.listener.controller.DopplerPluginController;

public interface DopplerPluginView extends ControllerPluginView<DopplerPluginController> {

    /**
     * 
     * @param x
     * @param y
     */
    void changeVelocity(float x, float y);
}
