package model.listener.plugin;


public interface ControllerPluginView<C> {

    /**
     * 
     * @param c
     */
    void setControllerApplication(C c);

    /**
     * TODO add parameter.
     */
    void setListenerControllerView();
}
