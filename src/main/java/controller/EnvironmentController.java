package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class EnvironmentController implements Initializable, ControllerView {
    private EnvironmentControllerApplications remote;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        remote = new EnvironmentControllerApplications();
    }

    @Override
    public void setControllerApplication(final MainControllerApplication ctrMain) {
        // TODO Auto-generated method stub

    }


}
