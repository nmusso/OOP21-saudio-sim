package model.listener.plugin;

import controller.view.ListenerView;

/**
 * 
 *
 * @param <C>
 */
public interface ControllerPluginView<C> {

    /**
     * 
     * @param controller 
     */
    void setControllerApplication(C controller);

    /**
     * @param view
     */
    void setListenerControllerView(ListenerView view);
}
