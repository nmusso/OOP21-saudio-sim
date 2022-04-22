package controller;

import view.controller.MainControllerView;

public class MainController implements ControllerApplication<MainControllerView> {

    private final EnvironmentController ctrEnvironment;
    private final ListenerController ctrListener;
    private final SourceController ctrSource;
    private final EqualizerController ctrEqualizer;
    private final SongController ctrSong;
    private final SpaceController ctrSpace;
    private MainControllerView ctrlView;


    public MainController() {
        this.ctrEnvironment = new EnvironmentController(this);
        this.ctrSource = new SourceController(this);
        this.ctrListener = new ListenerController(this);
        this.ctrEqualizer = new EqualizerController(this);
        this.ctrSong = new SongController(this);
        this.ctrSpace = new SpaceController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerView(final MainControllerView controllerView) {
        this.ctrlView = controllerView;
    }

    /**
     * Getter for EnvironmentController.
     * @return the controller
     */
    public EnvironmentController getEnvironmentController() {
        return this.ctrEnvironment;
    }

    /**
     * Getter for ListenerController.
     * @return the controller
     */
    public ListenerController getListenerCtr() {
        return this.ctrListener;
    }

    /**
     * Getter for SourceController.
     * @return the controller
     */
    public SourceController getSourceController() {
        return this.ctrSource;
    }

    /**
     * Getter for EqualizerController.
     * @return the controller
     */
    public EqualizerController getEqualizerController() {
        return this.ctrEqualizer;
    }

    /**
     * Getter for SongController.
     * @return the controller
     */
    public SongController getSongController() {
        return this.ctrSong;
    }

    /**
     * Getter for SpaceController.
     * @return the controller
     */
    public SpaceController getSpaceController() {
        return this.ctrSpace;
    }

    /**
     * Before the application ends call this method to close all necessary threads. 
     */
    public void closeAllThread() {
        this.ctrListener.stopAllPlugin();
    }

    /**
     * Display introduction messages.
     */
    public void showIntroduction() {
        ctrlView.showMessage("Use headphones for better experience");
    }
}
