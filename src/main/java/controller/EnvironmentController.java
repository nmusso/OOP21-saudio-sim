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
    private Environment env;

    public EnvironmentController(final MainController mainCtr) {
        AudioManager.initContext();
        this.mainCtr = mainCtr;
        env = envFac.createVoidEnvironment();
    }

    /**
     * 
     */
    public void addListener() {
        this.ctrlView.addSprite(TypeSprite.LISTENER, -1, env.getListener().getPosition());
    }

    /**
     * 
     * @return FRSource selected in env.
     */
    public FRSource getSelectedSource() {
        return this.env.getSourceHub().getSource(this.ctrlView.getLastSelectedSource());
    }

    /**
     * 
     * @return return env.
     */
    public Environment getEnv() {
        return this.env;
    }

    /**
     * 
     * @param source
     * @param type
     */
    public void addSourcetoSourceHub(final FRSource source, final TypeSprite type) {
        this.env.addSourceToSourceHub(source);
        this.ctrlView.addSprite(type, source.getId(), source.getPosition());
    }

    /**
     * 
     * @param source
     */
    public void removeSource(final FRSource source) {
        this.env.removeSourceFromSourceHub(source);
        this.ctrlView.removeSpriteSource();
    }

    /**
     * 
     * @param pos
     * @param id
     */
    public void moveSource(final Vec3f pos, final int id) {
        this.env.moveSource(this.env.getSourceHub().getSource(id), pos);
    }

    /**
     * 
     * @param pos
     */
    public void moveListener(final Vec3f pos) {
        this.env.getListener().setPosition(pos);
        this.listenerChangePos();
    }

    /**
     * 
     * @param preset
     */
    public void changeEnv(final String preset) {
        switch (preset) {
        case "cinema":
            this.env = envFac.createCinemaEnvironment();
            this.ctrlView.setTxBackGround(preset);
            break;
        case "mono":
            this.env = envFac.createMonoEnvironment();
            this.ctrlView.setTxBackGround("void");
            break;
        case "Stereo":
            this.env = envFac.createStereoEnvironment();
            this.ctrlView.setTxBackGround("void");
            break;
        case "HomeHIFI":
            this.env = envFac.createHIFIEnvironment();
            this.ctrlView.setTxBackGround(preset);
            break;
        default:
            this.env = envFac.createVoidEnvironment();
            this.ctrlView.setTxBackGround("void");
            break;
        }

        this.ctrlView.reset();
        this.mainCtr.getSpaceController().setSpinner(this.env.getSpace().getXmax(),
                this.getEnv().getSpace().getYmax());
        this.ctrlView.setSize(this.env.getSpace().getXmax(), this.getEnv().getSpace().getYmax());
        this.addListener();
        this.env.getSourceHub().getAll().stream().forEach(e -> {
            TypeSprite type = TypeSprite.SOURCEFULL;
            switch (e.getType()) {
            case FULL:
                type = TypeSprite.SOURCEFULL;
                break;
            case HIGH:
                type = TypeSprite.SOURCEHIGH;
                break;
            case LOW:
                type = TypeSprite.SOURCELOW;
                break;
            case MID:
                type = TypeSprite.SOURCEMID;
                break;
            default:
                break;
            }
            this.ctrlView.addSprite(type, e.getId(), e.getPosition());
        });
        this.mainCtr.getSourceController().updatePieChartData();
    }

    /**
     * 
     * @param x
     * @param y
     */
    public void setSizeEnv(final double x, final double y) {
        this.env.getSpace().setXMax((float) x);
        this.env.getSpace().setYMax((float) y);
        this.ctrlView.setSize(x, y);
    }

    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final EnvironmentControllerView controllerView) {
        ctrlView = controllerView;
        addListener();
    }

    /**
     * 
     * @param type
     */
    public void upgradeSourceType(final TypeSprite type) {
        this.ctrlView.upgradeTypeSpriteSource(type);
    }

    /**
     * 
     */
    private void listenerChangePos() {
        this.mainCtr.getListenerCtr().positionChanged();
    }

    /**
     * 
     */
    public void lastSelectedSourceChange() {
        this.mainCtr.getSourceController().changeSelectedSource();
    }

    /**
     * 
     * @param angle
     */
    public void changeOrientationListener(final float angle) {
        this.ctrlView.setAngleListener(angle);
    }

}
