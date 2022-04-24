package view.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import controller.EqualizerController;
import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import model.extension.effect.ALEffects;
import view.EqualizerView;

/**
 * Controller of the view EqualizerView.
 *
 */
public class EqualizerControllerView implements Initializable, ControllerView, EqualizerView {

    @FXML
    private GridPane slidersPane;
    @FXML
    private ToggleButton btnTurn;
    private EqualizerController ctrl;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrl = ctrMain.getEqualizerController();
        this.ctrl.setControllerView(this);
        initSliders();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String error) {
        final Alert alert = new Alert(AlertType.ERROR, error, ButtonType.OK);
        alert.show();
    }

    /**
     * Change color to the toggle button and enable/disable the sliders.
     * 
     * @param event the event who triggered the method
     */
    @FXML
    private void handlePress() { // NOPMD: Called by JavaFX
        final boolean state = btnTurn.isSelected();

        if (!state) {
            btnTurn.setText("OFF");
            btnTurn.setStyle("-fx-background-color: #900505");
            ctrl.removeEffect();
        } else {
            btnTurn.setText("ON");
            btnTurn.setStyle("-fx-background-color: #006B0D");
            final Alert alert = new Alert(AlertType.INFORMATION, "Keep the volume low while using the effects",
                    ButtonType.OK);
            alert.show();
        }

        getSliders().forEach(s -> s.setDisable(!state));
    }

    /**
     * Get all the sliders.
     * 
     * @return a list of sliders
     */
    private List<Slider> getSliders() {
        return slidersPane.getChildren().stream()
                .filter(node -> node instanceof Slider)
                .map(node -> (Slider) node)
                .collect(Collectors.toList());
    }

    /**
     * Set the property of the sliders.
     */
    private void initSliders() {
        getSliders().forEach(slider -> {
            final Optional<ALEffects> effect = ctrl.getEffect(slider.getId());
            if (effect.isPresent()) {
                final var eff = effect.get();
                slider.setMin(eff.getMinValue());
                slider.setMax(eff.getMaxValue());
                slider.valueProperty().addListener((obs, oldVal, newVal) -> {
                    ctrl.applyEffect(newVal.floatValue(), eff);
                });
            }
        });
    }
}
