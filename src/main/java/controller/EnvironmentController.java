    package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    public void addListener() {//TODO la posizione iniziale non è mai orretta bisogna aggiornala
        this.ctrlView.addSprite(TypeSprite.LISTENER, -1, new Vec3f(5.0f));
    }

    /**
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
        this.listenerChangePos();
    }

    /**
     * 
     */
    public void changeEnv() {
        this.env = envFac.createCinemaEnvironment();
        this.ctrlView.reset();
        this.env.getSourceHub().getAll().stream().forEach(e -> {
            //da fare il cotrollo per ogni source type
            this.ctrlView.addSprite(TypeSprite.SOURCEFULL, e.getId(), e.getPosition());
        });
        this.addListener();
    }

    /**
     * 
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
