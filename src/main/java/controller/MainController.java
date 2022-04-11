package controller;

import controller.view.MainControllerView;

public class MainController {

    private final EnvironmentController ctrEnvironment;
    private final ListenerController ctrListener;
    private final SourceController ctrSource;
    private final EqualizerController ctrEqualizer;
    private final SongController ctrSong;
    private final SpaceController ctrSpace;
    private MainControllerView controllerView;


    public MainController() {
        this.ctrEnvironment = new EnvironmentController(this);
        this.ctrSource = new SourceController(this);
        this.ctrListener = new ListenerController(this);
        this.ctrEqualizer = new EqualizerController(this);
        this.ctrSong = new SongController(this);
        this.ctrSpace = new SpaceController(this);
    }

    /**
     * 
     */
    public void setControllerView(final MainControllerView controllerView) {
        this.controllerView = controllerView;
    }

    /**
     * 
     * @return
     */
    public EnvironmentController getEnvironmentController() {
        return this.ctrEnvironment;
    }

    /**
     * 
     * @return
     */
    public ListenerController getListenerCtr() {
        return this.ctrListener;
    }

    /**
     * 
     * @return
     */
    public SourceController getSourceController() {
        return this.ctrSource;
    }

    /**
     * 
     * @return
     */
    public EqualizerController getEqualizerController() {
        return this.ctrEqualizer;
    }

    /**
     * 
     * @return
     */
    public SongController getSongController() {
        return this.ctrSong;
    }
    
    /**
     * 
     * @return
     */
    public SpaceController getSpaceController() {
        return this.ctrSpace;
    }
}
