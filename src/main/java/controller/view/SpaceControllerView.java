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
    private Spinner<Double> spnSizeLenght;
    private Double lenght;
    @FXML
    private Spinner<Double> spnSizeWidth;
    private Double width;
    @FXML
    private Spinner<Double> spnScale;
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
        spnSizeWidth.setValueFactory(new DoubleSpinnerValueFactory(1, 100, 10, 0.5));
        spnSizeLenght.setValueFactory(new DoubleSpinnerValueFactory(1, 100, 10, 0.5));
        addPresetTocmb();
    }

    private void addPresetTocmb() {
        final List<String> listPreset = new ArrayList<>();
        listPreset.add("nothing");
        listPreset.add("cinema");
        listPreset.add("stadio");
        final ObservableList<String> comboItems = FXCollections.observableArrayList(listPreset);
        cmbPreset.setItems(comboItems);
    }

    @FXML public final void handleBtnAddEnv(final Event event) {
        System.out.println("addEnv");
     }
    
    @FXML public final void handleBtnDelEnv(final Event event) {
        System.out.println("addEnv");
     }

    @FXML public final void handleCmbPreset(final Event event) {
        selectedPreset = cmbPreset.getSelectionModel().getSelectedItem().toString();
        if(selectedPreset.equals("cinema")) {
            this.ctrl.changePreset();
        }
        System.out.println(selectedPreset);
    }

    @FXML public final void handleUpdateSpin(final Event event) {
        lenght = spnSizeLenght.getValue();
        width = spnSizeWidth.getValue();
        this.ctrl.setSize(lenght, width);
    }

    /**
     * 
     * 
     */
    public Double getLenght() {
        return lenght;
    }

    /**
     * 
     * 
     */
    public void setLenght(final Double lenght) {
        this.lenght = lenght;
    }

    /**
     * 
     * 
     */
    public Double getWidth() {
        return width;
    }

    /**
     * 
     * 
     */
    public void setWidth(final Double width) {
        this.width = width;
    }

    /**
     * 
     * 
     */
    public String getSelectedPreset() {
        return selectedPreset;
    }

    @Override
    public void showError(String error) {
        // TODO Auto-generated method stub
        
    }

}
