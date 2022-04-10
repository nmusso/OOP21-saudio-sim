package controller;

import controller.view.MainControllerView;

public class MainController {

    private final EnvironmentController ctrEnv;
    private final ListenerController ctrListener;
    private final SourceController ctrSource;
    private final EqualizerController ctrEqualizer;
    private final SongController ctrSong;
    private MainControllerView controllerView;


    public MainController() {
        this.ctrSource = new SourceController(this);
        this.ctrListener = new ListenerController(this);
        this.ctrEqualizer = new EqualizerController(this);
        this.ctrEnv = new EnvironmentController(this);
        this.ctrSong = new SongController(this);
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
    public EnvironmentController getEnvironmentCtr() {
        return this.ctrEnv;
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
}
