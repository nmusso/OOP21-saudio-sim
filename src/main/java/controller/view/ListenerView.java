package controller.view;

import javafx.scene.control.Tab;

public interface ListenerView {

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

    /**
     * Show an error.
     * @param error  the error message
     */
    void showError(String error); 
}
