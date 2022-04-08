package controller.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SpaceControllerView  implements Initializable, ControllerView{

    @Override
    public void setControllerApplication(MainController ctrMain) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    
    @FXML public final void handleTestStart(final Event event) {
        System.out.println("test");
     }
}
