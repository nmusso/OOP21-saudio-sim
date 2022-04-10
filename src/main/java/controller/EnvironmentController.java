package controller;

import controller.view.EnvironmentControllerView;
import model.source.FRSource;
import model.source.Source;

public class EnvironmentController {

    private final MainController mainCtr;
    private EnvironmentControllerView ctrlView;
    
    public EnvironmentControllerView getCtrlView() {
        return ctrlView;
        
    }

    public MainController getMainCtr() {
        return mainCtr;
    }

    public EnvironmentController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }
    
    public FRSource getSelectedSource() {
        //this.ctrlView.getLastSelect().getId();
        return null;
    }

    /**
     * 
     * 
     */
    public void addEnvironment() {
    }
    
    
    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final EnvironmentControllerView controllerView) {
        ctrlView = controllerView;
    }

}
