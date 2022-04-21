package plugin.listener.view;

import plugin.listener.controller.DopplerPluginController;

/**
 * 
 * View for DopplerPlugin.
 *
 */
public interface DopplerPluginView extends ControllerPluginView<DopplerPluginController> {

    /**
     * Update velocity in x and y axes.
     * @param x 
     * @param y
     */
    void changeVelocity(float x, float y);
}
