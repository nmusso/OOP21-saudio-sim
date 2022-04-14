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
     * @param lenght
     * @param width
     */
    public void setSize(final double lenght, final double width) {
        this.mainCtr.getEnvironmentController().setSizeEnv(lenght, width);
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
     * @param lenght
     * @param width
     */
    public void setSpinner(final double lenght, final double width) {
        this.ctrlView.setLenght(lenght);
        this.ctrlView.setWidth(width);
    }

}
