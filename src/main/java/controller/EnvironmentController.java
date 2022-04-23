package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import model.audiomanager.AudioManager;
import model.environment.Environment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.source.FRSource;
import model.utility.Vec3f;
import view.EnvironmentView;
import view.utility.TypeSprite;

public class EnvironmentController implements ControllerApplication<EnvironmentView> {

    private static final String PATH = "/json/data.json";

    private final MainController mainCtr;
    private EnvironmentView ctrlView;
    private final EnvironmentFactory envFac = new EnvironmentFactoryImpl();
    private Environment env;

    /**
     * Constructor of the EnvironmentController, and initialize env void.
     * 
     * @param mainCtr the main controller.
     */
    public EnvironmentController(final MainController mainCtr) {
        AudioManager.initContext();
        this.mainCtr = mainCtr;
        this.env = envFac.createVoidEnvironment();
    }

    /**
     * Add listener to ctrlView.
     * 
     */
    public void addListener() {
        this.ctrlView.addSprite(TypeSprite.LISTENER, (int) env.getListener().getCurrentContext().getId(), env.getListener().getPosition());
    }

    /**
     * Get last selected source.
     * 
     * @return FRSource selected in this.env.
     */
    public Optional<FRSource> getSelectedSource() {
        return this.env.getSourcesHub().getSource(this.ctrlView.getLastSelectedSource());
    }

    /**
     * Get current environment.
     * 
     * @return return this.env.
     */
    public Environment getEnv() {
        return this.env;
    }

    /**
     * Add source at sourcehub of Env, and add source with typeSprite to canvas of
     * ctrlView.
     * 
     * @param source to add
     * @param type   to draw
     */
    public void addSourcetoSourcesHub(final FRSource source, final TypeSprite type) {
        this.env.addSourceToSourcesHub(source);
        this.ctrlView.addSprite(type, source.getId(), source.getPosition());
    }

    /**
     * remove source from sourcehub and from canvas.
     * 
     * @param source to remove
     */
    public void removeSource(final FRSource source) {
        this.env.removeSourceFromSourcesHub(source);
        this.ctrlView.removeSpriteSource();
    }

    /**
     * Move the source with passed id, in to pos.
     * 
     * @param pos new
     * @param id  source to move.
     */
    public void moveSource(final Vec3f pos, final int id) {
        final Optional<FRSource> s = this.env.getSourcesHub().getSource(id);
        if (s.isPresent()) {
            this.env.moveSource(s.get(), pos);
        }
    }

    /**
     * Move listener in Env, signals its displacement.
     * 
     * @param pos new position to move
     */
    public void moveListener(final Vec3f pos) {
        this.env.getListener().setPosition(pos);
        this.mainCtr.getListenerCtr().positionChanged();
    }

    /**
     * Change type and background of Environment.
     * 
     * @param preset has type env.
     */
    public void changeEnv(final String preset) {
        if (!"Empty".equals(preset)) {
            String json;
            try (InputStream is = getClass().getResourceAsStream(PATH)) {
                json = new String(is.readAllBytes());
                this.env = envFac.createEnvironmentFromJson(json, preset);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            this.env = envFac.createVoidEnvironment();
        }
        this.ctrlView.setTxBackGround(preset);

        this.ctrlView.reset();
        this.mainCtr.getSpaceController().setSpinner(this.env.getSpace().getXmax(), this.getEnv().getSpace().getYmax());
        this.ctrlView.setSize(this.env.getSpace().getXmax(), this.getEnv().getSpace().getYmax());
        addListener();
        this.env.getSourcesHub().getAll().stream().forEach(e -> {
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
     * 
     * @param x to Xmax
     * @param y to Ymax
     */
    public void setSizeEnv(final double x, final double y) {
        this.env.getSpace().setXMax((float) x);
        this.env.getSpace().setYMax((float) y);
        this.ctrlView.setSize(x, y);
    }

    /**
     *{@inheritDoc}
     */
    public void setControllerView(final EnvironmentView controllerView) {
        ctrlView = controllerView;
        this.changeEnv("Empty");
    }

    /**
     * Change last selected source in new type.
     * 
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
