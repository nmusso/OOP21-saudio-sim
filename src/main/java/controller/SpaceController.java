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
     * @param x
     * @param y
     */
    public void setSize(final double x, final double y) {
        this.mainCtr.getEnvironmentController().setSizeEnv(x, y);
    }

    /**
     * 
     * @param preset
     */
    public void changePreset(final String preset) {
        this.mainCtr.getEnvironmentController().changeEnv(preset);
    }

    /**
     * 
     * @param x
     * @param y
     */
    public void setSpinner(final double x, final double y) {
        this.ctrlView.setX(x);
        this.ctrlView.setY(y);
    }

}
