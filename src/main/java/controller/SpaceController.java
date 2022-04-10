package controller;

import controller.view.SpaceControllerView;

public class SpaceController {

    private final MainController mainCtr;
    private SpaceControllerView ctrlView;

    public SpaceController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * 
     * 
     */
    public void addEnvironment() {
    }
    
    
    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final SpaceControllerView controllerView) {
        ctrlView = controllerView;
    }

}
