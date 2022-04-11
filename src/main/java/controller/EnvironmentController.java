    package controller;

import controller.view.EnvironmentControllerView;
import model.audiomanager.AudioManager;
import model.environment.Environment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.source.FRSource;
import model.utility.Vec3f;
import view.utility.TypeSprite;

public class EnvironmentController implements ControllerApplication<EnvironmentControllerView> {

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
        return this.env.getSourceHub().getSource(this.ctrlView.getLastSelectedSource());
    }

    /**
     * 
     */
    public Environment getEnv() {
        return this.env;
    }

    /**
     * 
     */
    public void addSourcetoSourceHub(final FRSource source, final TypeSprite type) {
        this.env.addSourceToSourceHub(source);
        this.ctrlView.addSprite(type, source.getId(), source.getPosition());
    }

    /**
     * 
     */
    public void removeSource(final FRSource source) {
        this.env.removeSourceFromSourceHub(source);
        source.delete();
        this.ctrlView.removeSpriteSource();
    }

    /**
     * 
     */
    public void moveSource(final Vec3f pos, final int id) {
        this.env.moveSource(this.env.getSourceHub().getSource(id), pos);
    }

    /**
     *
     */
    public void moveListener(final Vec3f pos) {
        this.env.getListener().setPosition(pos);
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
        //TODO come utilizzare lo space
        this.ctrlView.setLenght(length);
    }

    /**
     * 
     */
    public void setWidthEnv(final double width) {
      //TODO come utilizzare lo space
        this.ctrlView.setWidth(width);
    }

    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final EnvironmentControllerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * 
     */
    public void upgradeSourceType(final TypeSprite type) {
        this.ctrlView.upgradeTypeSpriteSource(type);
    }

    /**
     * 
     */
    public void lastSelectedSourceChange() {
        this.mainCtr.getSourceController().changeSelectedSource();
    }

}
