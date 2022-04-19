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
        addPresetTocmb();
    }

    /**
     * Add String preset to comboBox.
     */
    private void addPresetTocmb() {
        final List<String> listPreset = new ArrayList<>();
        listPreset.add("nothing");
        listPreset.add("mono");
        listPreset.add("Stereo");
        listPreset.add("cinema");
        listPreset.add("HomeHIFI");
        final ObservableList<String> comboItems = FXCollections.observableArrayList(listPreset);
        cmbPreset.setItems(comboItems);
        cmbPreset.setPromptText("nothing");
    }

    /**
     * event on click BTNADD disabled.
     * @param event
     */
    @FXML public final void handleBtnAddEnv(final Event event) {
        System.out.println("addEnv");
     }

    /**
     * event on click BTNDEL disabled.
     * @param event
     */
    @FXML public final void handleBtnDelEnv(final Event event) {
        System.out.println("removeEnv");
     }

    /**
     * event on choose of preset from cmb, signals to the controller that the selection has changed.
     * @param event
     */
    @FXML public final void handleCmbPreset(final Event event) {
        selectedPreset = cmbPreset.getSelectionModel().getSelectedItem().toString();
        this.ctrl.changePreset(selectedPreset);
    }

    /**
     * changes with the new spinner values and signals the controller of the change.
     * @param event
     */
    @FXML public final void handleUpdateSpin(final Event event) {
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
     * @param x
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
    * @param y
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
    public void showError(final String error) {

    }

}
