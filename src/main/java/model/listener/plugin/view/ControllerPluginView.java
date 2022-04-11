package model.listener.plugin.view;

import controller.view.ListenerControllerView;

public interface ControllerPluginView<C> {

    /**
     * 
     * @param c
     */
    void setControllerApplication(C c);

    /**
     * TODO add parameter.
     */
    void setListenerControllerView(ListenerControllerView view);
}
