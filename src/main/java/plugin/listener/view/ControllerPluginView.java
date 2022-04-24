package plugin.listener.view;

import view.ListenerView;

/**
 * The FXML controller for plug-in view.
 * @param <C> the ControllerPlugin specific class type.
 */
public interface ControllerPluginView<C> {

    /**
     * Set the specific controller.
     * @param controller plug-in controller.
     */
    void setControllerApplication(C controller);

    /**
     * Set the listener view.
     * @param view the view of listener.
     */
    void setListenerControllerView(ListenerView view);
}
