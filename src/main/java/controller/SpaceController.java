package controller;

import controller.view.SpaceControllerView;

public class SpaceController implements ControllerApplication<SpaceControllerView> {

    private final MainController mainCtr;
    private SpaceControllerView ctrlView;

    /**
     * Construct a new SpaceController.
     * @param mainCtr the MainController
     */
    public SpaceController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * Set the controller of the view.
     * @param controllerView  the controller view
     */
    public void setControllerView(final SpaceControllerView controllerView) {
        ctrlView = controllerView;
    }
 
    /**
     * Set new size in Env changed by spaceView.
     * @param x
     * @param y
     */
    public void setSize(final double x, final double y) {
        this.mainCtr.getEnvironmentController().setSizeEnv(x, y);
    }

    /**
     * Set new Preset for Env changed by spaceView.
     * @param preset
     */
    public void changePreset(final String preset) {
        this.mainCtr.getEnvironmentController().changeEnv(preset);
    }

    /**
     * Set value for spinner in spaceView.
     * @param x
     * @param y
     */
    public void setSpinner(final double x, final double y) {
        this.ctrlView.setX(x);
        this.ctrlView.setY(y);
    }

    /**
     * Enable/disable the objects.
     * @param state the state of the objects
     */
    public void disableChange(final boolean state) {
        ctrlView.disableCombo(state);
    }
}
