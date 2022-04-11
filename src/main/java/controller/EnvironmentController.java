package controller;

import controller.view.EnvironmentControllerView;
import model.audiomanager.AudioManager;
import model.environment.EnvironmentFactoryImpl;
import model.source.FRSource;
import model.source.Source;

public class EnvironmentController {

    private final MainController mainCtr;
    private EnvironmentControllerView ctrlView;

    public EnvironmentController(final MainController mainCtr) {
        AudioManager.initContext();
        this.mainCtr = mainCtr;
    }

    /**
     * 
     * 
     */
    public FRSource getSelectedSource() {
        //this.ctrlView.getLastSelect().getId();
        return null;
    }

    /**
     * 
     */
    public void addEnvironment() {
    }

    /**
     * 
     */
    public void setLenghtEnv(final double length) {
        this.ctrlView.setLenght(length);
    }

    /**
     * 
     */
    public void setWidthEnv(final double width) {
        this.ctrlView.setWidth(width);
    }

    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final EnvironmentControllerView controllerView) {
        ctrlView = controllerView;
    }

}
