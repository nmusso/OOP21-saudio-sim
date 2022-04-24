package view.controller;

import java.net.URL;
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
import view.SpaceView;

/**
 * Controller of the view SpaceView.
 *
 */
public class SpaceControllerView  implements Initializable, ControllerView, SpaceView { 

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

    private static final double MINVALUESPIN = 1;
    private static final double MAXVALUESPIN = 100;
    private static final double STEPVALUESPIN = 0.5;


    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrl = ctrMain.getSpaceController();
        this.ctrl.setControllerView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        spnSizeX.setValueFactory(new DoubleSpinnerValueFactory(MINVALUESPIN, MAXVALUESPIN, 10, STEPVALUESPIN));
        spnSizeY.setValueFactory(new DoubleSpinnerValueFactory(MINVALUESPIN, MAXVALUESPIN, 10, STEPVALUESPIN));
    }

    /**
     * {@inheritDoc}
     */
    public void addPresetTocmb(final List<String> listPreset) {
        final ObservableList<String> comboItems = FXCollections.observableArrayList(listPreset);
        cmbPreset.setItems(comboItems);
        cmbPreset.setPromptText("Empty");
    }

    /**
     * event on choose of preset from cmb, signals to the controller that the selection has changed.
     * @param event the event who trigger the method
     */
    @FXML 
    private void handleCmbPreset(final Event event) { // NOPMD: Called by JavaFX
        selectedPreset = cmbPreset.getSelectionModel().getSelectedItem().toString();
        this.ctrl.changePreset(selectedPreset);
    }

    /**
     * changes with the new spinner values and signals the controller of the change.
     * @param event the event who trigger the method
     */
    @FXML
    private void handleUpdateSpin(final Event event) { // NOPMD: Called by JavaFX
        x = spnSizeX.getValue();
        y = spnSizeY.getValue();
        this.ctrl.setSize(x, y);
    }

    /**
     * Get value of spinnerX.
     * @return x double.
     */
    public double getX() {
        return x;
    }

    /**
     * Set value passed by the controller to both the global variable and the spinner .
     * @param x width double
     */
    @Override
    public void setX(final double x) {
        this.x = x;
        spnSizeX.setValueFactory(new DoubleSpinnerValueFactory(MINVALUESPIN, MAXVALUESPIN, x, STEPVALUESPIN));
    }

    /**
     * Get value of spinnerY.
     * @return y double.
     */
    public double getY() {
        return y;
    }

   /**
    * Set value passed by the controller to both the global variable and the spinner Y.
    * @param y height double
    */
    @Override
    public void setY(final double y) {
        this.y = y;
        spnSizeY.setValueFactory(new DoubleSpinnerValueFactory(MINVALUESPIN, MAXVALUESPIN, y, STEPVALUESPIN));
    }

    /**
     * Get preset current selected.
     * @return current preset
     */
    public String getSelectedPreset() {
        return selectedPreset;
    }

    /**
     * Disable/enable the combobox with the presets.
     * @param newState the new state of the combobox
     */
    @Override
    public void disableCombo(final boolean newState) {
        cmbPreset.setDisable(newState);
    }

    @Override
    public void showMessage(final String error) {

    }

}
