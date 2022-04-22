package controller.view;

import javafx.scene.control.Tab;

/**
 * 
 * View for the Listener.
 *
 */
public interface ListenerView extends ControllerView {

    /**
     * Add the plug-in view tab to the view.
     * @param tab
     */
    void setPlugin(Tab tab);

    /**
     * Remove the plug-in view tab from the view.
     * @param tab
     */
    void removePlugin(Tab tab);

    /**
     * Update the position in the view.
     */
    void positionChanged();

}
