package controller.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.MainController;
import controller.SpaceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;

public class SpaceControllerView  implements Initializable, ControllerView { 

    private SpaceController ctrl;
    @FXML
    private Spinner<Double> spnSizeX;
    private double x;
    @FXML
    private Spinner<Double> spnSizeY;
    private double y;
    @FXML
    private ComboBox<String> cmbPreset;
    private String selectedPreset;

    /**
     * 
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrl = ctrMain.getSpaceController();
        this.ctrl.setControllerView(this);
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        spnSizeX.setValueFactory(new DoubleSpinnerValueFactory(1, 100, 10, 0.5));
        spnSizeY.setValueFactory(new DoubleSpinnerValueFactory(1, 100, 10, 0.5));
        addPresetTocmb();
    }

    private void addPresetTocmb() {
        final List<String> listPreset = new ArrayList<>();
        listPreset.add("nothing");
        listPreset.add("mono");
        listPreset.add("Stereo");
        listPreset.add("cinema");
        listPreset.add("HomeHIFI");
        final ObservableList<String> comboItems = FXCollections.observableArrayList(listPreset);
        cmbPreset.setItems(comboItems);
        cmbPreset.setPromptText("mono");
    }

    @FXML public final void handleBtnAddEnv(final Event event) {
        System.out.println("addEnv");
     }
    @FXML public final void handleBtnDelEnv(final Event event) {
        System.out.println("removeEnv");
     }

    @FXML public final void handleCmbPreset(final Event event) {
        selectedPreset = cmbPreset.getSelectionModel().getSelectedItem().toString();
        this.ctrl.changePreset(selectedPreset);
    }

    @FXML public final void handleUpdateSpin(final Event event) {
        x = spnSizeX.getValue();
        y = spnSizeY.getValue();
        this.ctrl.setSize(x, y);
    }

    /**
     * 
     * @return TODO
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @param x
     */
    public void setX(final double x) {
        this.x = x;
        spnSizeX.setValueFactory(new DoubleSpinnerValueFactory(1, 100, this.x, 0.5));
    }

    /**
     * 
     * @return TODO
     */
    public double getY() {
        return y;
    }

   /**
    * 
    * @param y
    */
    public void setY(final double y) {
        this.y = y;
        spnSizeY.setValueFactory(new DoubleSpinnerValueFactory(1, 100, this.y, 0.5));
    }

    /**
     * 
     * @return TODO
     */
    public String getSelectedPreset() {
        return selectedPreset;
    }

    @Override
    public void showError(final String error) {
        // TODO Auto-generated method stub
    }

}
