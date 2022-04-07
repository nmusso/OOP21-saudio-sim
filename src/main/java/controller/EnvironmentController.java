package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class EnvironmentController implements Initializable {
    private EnvironmentControllerApplications remote;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        remote = new EnvironmentControllerApplications();
    }

    
}
