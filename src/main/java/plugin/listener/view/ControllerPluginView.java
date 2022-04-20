package plugin.listener.view;

import controller.view.ListenerView;

/**
 * The fxml controller for plug-in view.
 * @param <C> the ControllerPlugin specific class type.
 */
public interface ControllerPluginView<C> {

    /**
     * Set the specific controller.
     * @param controller 
     */
    void setControllerApplication(C controller);

    /**
     * Set the listener view.
     * @param view
     */
    void setListenerControllerView(ListenerView view);
}
