package model.listener.plugin;

import controller.view.ListenerView;

public interface ControllerPluginView<C> {

    /**
     * 
     * @param c
     */
    void setControllerApplication(C c);

    /**
     * TODO add parameter.
     */
    void setListenerControllerView(ListenerView view);
}
