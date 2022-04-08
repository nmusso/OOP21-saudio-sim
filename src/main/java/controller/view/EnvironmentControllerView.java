package controller.view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.EnvironmentController;
import controller.MainController;
import javafx.fxml.Initializable;

public class EnvironmentControllerView implements Initializable, ControllerView {
    private EnvironmentControllerView remote;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        remote = new EnvironmentControllerView();
    }

    @Override
    public void setControllerApplication(final MainController ctrMain) {
        // TODO Auto-generated method stub

    }


}
