package controller;

public class MainController {

    private final EnvironmentController ctrEnv;
    private final ListenerController ctrListener;
    /*Other Controllers*/

    public MainController() {
        this.ctrEnv = new EnvironmentController();
        this.ctrListener = new ListenerController();
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
}
