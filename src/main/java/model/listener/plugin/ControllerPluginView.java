package model.listener.plugin;

import controller.view.ListenerView;

public interface ControllerPluginView<C> {

    /**
     * 
     * @param c
     */
    void setControllerApplication(C c);

    /**
     * @param view
     */
    void setListenerControllerView(ListenerView view);
}
