package controller;

import controller.view.ListenerControllerView;

public class ListenerController {
    private ListenerControllerView controllerView;

    private final MainController mainCtr;
    public ListenerController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * 
     */
    public void setControllerView(final ListenerControllerView controllerView) {
        this.controllerView = controllerView;
    }
}
