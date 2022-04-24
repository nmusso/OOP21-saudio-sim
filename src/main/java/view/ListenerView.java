package view;

import javafx.scene.control.Tab;
import view.controller.ControllerView;

/**
 * 
 * View for the Listener.
 *
 */
public interface ListenerView extends ControllerView {

    /**
     * Add the plug-in view tab to the view.
     * @param tab the tab view
     */
    void setPlugin(Tab tab);

    /**
     * Remove the plug-in view tab from the view.
     * @param tab the tab view
     */
    void removePlugin(Tab tab);

    /**
     * Update the position in the view.
     */
    void positionChanged();

}
