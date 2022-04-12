package controller.view;

import controller.MainController;

public interface ControllerView {

    /**
     * 
     * @param ctrMain
     */
    void setControllerApplication(MainController ctrMain);
    
    /**
     * 
     * @param error
     */
    void showError(String error); 
}
