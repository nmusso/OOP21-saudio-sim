package controller;

public class MainController {

    private final EnvironmentController ctrEnv;
    private final ListenerController ctrListener;
    private final SourceController ctrSource;
    private final EqualizerController ctrEquailzer;
    /*Other Controllers*/

    public MainController() {
        this.ctrSource = new SourceController();
        this.ctrListener = new ListenerController();
        this.ctrEquailzer = new EqualizerController();
        this.ctrEnv = new EnvironmentController(ctrListener, ctrSource);
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
        return this.ctrEquailzer;
    }
}
