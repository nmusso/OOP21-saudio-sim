package controller.view;

import javafx.scene.control.Tab;

public interface ListenerView extends ControllerView {

    /**
     * @param tab
     */
    void setPlugin(Tab tab);

    /**
     * 
     * @param tab
     */
    void removePlugin(Tab tab);

    /**
     * 
     */
    void positionChanged();

}