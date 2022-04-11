package controller;

import controller.view.EnvironmentControllerView;
import model.audiomanager.AudioManager;
import model.environment.Environment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.source.FRSource;
import model.source.Source;

public class EnvironmentController {

    private final MainController mainCtr;
    private EnvironmentControllerView ctrlView;
    private final EnvironmentFactory envFac = new EnvironmentFactoryImpl();
    private final Environment env;

    public EnvironmentController(final MainController mainCtr) {
        AudioManager.initContext();
        this.mainCtr = mainCtr;
        env = envFac.createVoidEnvironment();
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
