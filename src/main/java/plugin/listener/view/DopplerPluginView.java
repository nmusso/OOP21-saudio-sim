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
     * @param x velocity in x axes. 
     * @param y velocity in y axes.
     */
    void changeVelocity(float x, float y);
}
