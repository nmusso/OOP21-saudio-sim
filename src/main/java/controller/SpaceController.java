package controller;

import controller.view.SpaceControllerView;

public class SpaceController {

    private final MainController mainCtr;
    private SpaceControllerView ctrlView;

    public SpaceController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }    
    
    public SpaceControllerView getCtrlView() {
        return ctrlView;
    }

    public MainController getMainCtr() {
        return mainCtr;
    }

    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final SpaceControllerView controllerView) {
        ctrlView = controllerView;
    }

}
