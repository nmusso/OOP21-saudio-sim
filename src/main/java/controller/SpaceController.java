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
    public void setSize(final double lenght, final Double width) {
        this.mainCtr.getEnvironmentController().setSizeEnv(lenght, width);
    }

    public void changePreset() {
        this.mainCtr.getEnvironmentController().changePreset();
    }

}
