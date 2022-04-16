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

    /**
     * Constructor of the EnvironmentController, and initialize env void.
     * @param mainCtr
     */
    public EnvironmentController(final MainController mainCtr) {
        AudioManager.initContext();
        this.mainCtr = mainCtr;
        this.env = envFac.createVoidEnvironment();
    }

    /**
     * Add listener to ctrlView.
     * @param type of listener.
     */
    public void addListener(final TypeSprite type) {
        this.ctrlView.addSprite(type, -1, env.getListener().getPosition());
    }

    /**
     * Get last selected source.
     * @return FRSource selected in env.
     */
    public FRSource getSelectedSource() {
        return this.env.getSourceHub().getSource(this.ctrlView.getLastSelectedSource());
    }

    /**
     * Get current environment.
     * @return return env.
     */
    public Environment getEnv() {
        return this.env;
    }

    /**
     * Add source at sourcehub of Env, and add source with typeSprite to canvas of ctrlView.
     * @param source to add
     * @param type to draw
     */
    public void addSourcetoSourceHub(final FRSource source, final TypeSprite type) {
        this.env.addSourceToSourceHub(source);
        this.ctrlView.addSprite(type, source.getId(), source.getPosition());
    }

    /**
     * remove source from sourcehub and from canvas.
     * @param source to remove
     */
    public void removeSource(final FRSource source) {
        this.env.removeSourceFromSourceHub(source);
        this.ctrlView.removeSpriteSource();
    }

    /**
     * Move the source with passed id, in to pos.
     * @param pos new
     * @param id source to move.
     */
    public void moveSource(final Vec3f pos, final int id) {
        this.env.moveSource(this.env.getSourceHub().getSource(id), pos);
    }

    /**
     * Move listener in Env, signals its displacement.
     * @param pos
     */
    public void moveListener(final Vec3f pos) {
        this.env.getListener().setPosition(pos);
        this.mainCtr.getListenerCtr().positionChanged();
    }

    /**
     * Change type and background of Environment.
     * @param preset has type env.
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
//        if ("cinema".equals(preset)) {
//            addListener(TypeSprite.LISTENERCINEMA);
//        } else {
//            addListener(TypeSprite.LISTENER);
//        }
        addListener(TypeSprite.LISTENER);
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
     * Set new dimension of env space.
     * @param x to Xmax
     * @param y to Ymax
     */
    public void setSizeEnv(final double x, final double y) {
        this.env.getSpace().setXMax((float) x);
        this.env.getSpace().setYMax((float) y);
        this.ctrlView.setSize(x, y);
    }

    /**
     * {@inheritDoc}
     * @param controllerView
     */
    public void setControllerView(final EnvironmentControllerView controllerView) {
        ctrlView = controllerView;
        addListener(TypeSprite.LISTENER);
    }

    /**
     * Change last selected source in new type.
     * @param type new
     */
    public void upgradeSourceType(final TypeSprite type) {
        this.ctrlView.upgradeTypeSpriteSource(type);
    }

    /**
     * notifies the SourceController of the change of the last selected source.
     */
    public void lastSelectedSourceChange() {
        this.mainCtr.getSourceController().updateSelectedSource();
    }
}
