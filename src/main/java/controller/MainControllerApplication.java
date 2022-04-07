package controller;

public class MainControllerApplication {

    private final EnvironmentControllerApplications ctrEnv;
    private final ListenerControllerApplication ctrListener;
    /*Other Controllers*/

    public MainControllerApplication() {
        this.ctrEnv = new EnvironmentControllerApplications();
        this.ctrListener = new ListenerControllerApplication();
    }

    /**
     * 
     * @return
     */
    public EnvironmentControllerApplications getEnvironmentCtr() {
        return this.ctrEnv;
    }

    /**
     * 
     * @return
     */
    public ListenerControllerApplication getListenerCtr() {
        return this.ctrListener;
    }
}
