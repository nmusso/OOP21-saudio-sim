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
        source.delete();
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
        //TODO da mettere lo sfondo
        switch (preset) {
        case "cinema":
            this.env = envFac.createCinemaEnvironment();
            break;
        case "mono":
            this.env = envFac.createMonoEnvironment();
            break;
        case "Stereo":
            this.env = envFac.createStereoEnvironment();
            break;
        case "stadio":
            this.env = envFac.createStadiumEnvironment();
            break;
        case "HomeHIFI":
            this.env = envFac.createHIFIEnvironment();
            break;
        case "Demo":
            this.env = envFac.createVoidEnvironment();
            break;
        default:
            this.env = envFac.createVoidEnvironment();
            break;
        }

        this.ctrlView.reset();
        this.mainCtr.getSpaceController().setSpinner(this.env.getSpace().getLenght(),
                this.getEnv().getSpace().getWidth());
        this.ctrlView.setSize(this.env.getSpace().getWidth(), this.getEnv().getSpace().getLenght());
        this.addListener();
        this.env.getSourceHub().getAll().stream().forEach(e -> {
            // da fare il cotrollo per ogni source type
            this.ctrlView.addSprite(TypeSprite.SOURCEFULL, e.getId(), e.getPosition());
        });
    }

    /**
     * 
     * @param length
     * @param width
     */
    public void setSizeEnv(final double length, final double width) {
        this.ctrlView.setSize(length, width);
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

//    public void changePreset() {
//        FileInputStream file=null;
//        try {
//            file = new FileInputStream(new File("src/main/resources/img/" + "sfondoCinema" + ".png"));
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        this.ctrlView.changePreset(file);
//    }

}
