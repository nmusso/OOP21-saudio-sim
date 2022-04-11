package controller;

import controller.view.SpaceControllerView;

public class SpaceController implements ControllerApplication<SpaceControllerView> {

    private final MainController mainCtr;
    private SpaceControllerView ctrlView;

    public SpaceController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final SpaceControllerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * 
     */
    public void setLenght(final double lenght) {
        this.mainCtr.getEnvironmentController().setLenghtEnv(lenght);
    }

    /**
     * 
     */
    public void setWidth(final double width) {
        this.mainCtr.getEnvironmentController().setWidthEnv(width);
    }

}
