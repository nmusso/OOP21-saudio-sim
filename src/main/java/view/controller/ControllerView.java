package view.controller;

import controller.MainController;

/**
 * Interface for the controller of the views.
 *
 */
public interface ControllerView {

    /**
     * Set the app controller to the view controller.
     * @param ctrMain  the main controller containing all the app controllers
     */
    void setControllerApplication(MainController ctrMain);

    /**
     * Display a message.
     * @param message  the message
     */
    void showMessage(String message); 
}
