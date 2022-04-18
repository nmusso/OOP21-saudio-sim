package plugin.listener.view;

import model.listener.plugin.ControllerPluginView;
import model.utility.Vec3f;
import plugin.listener.controller.SoundLevelMeterPluginController;

public interface SoundLevelMeterPluginView extends ControllerPluginView<SoundLevelMeterPluginController> {
    /**
     * 
     * @param rgb
     */
    void setColor(Vec3f rgb);
}
