package plugin.listener.view;

import model.utility.Vec3f;
import plugin.listener.controller.SoundLevelMeterPluginController;

/**
 * 
 * View for SoundLevelMeterPlugin.
 *
 */
public interface SoundLevelMeterPluginView extends ControllerPluginView<SoundLevelMeterPluginController> {
    /**
     * Update the color of visual indicator.
     * @param rgb
     */
    void setColor(Vec3f rgb);
}
