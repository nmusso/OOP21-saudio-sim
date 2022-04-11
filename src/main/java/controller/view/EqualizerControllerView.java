package controller.view;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import controller.EqualizerController;
import controller.MainController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import model.effect.ALEffects;

/**
 * Controller of the view EqualizerView.
 *
 */
public class EqualizerControllerView implements Initializable, ControllerView {

    @FXML private GridPane slidersPane;
    @FXML private ToggleButton btnTurn;
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
     * Change color to the toggle button and enable/disable the sliders.
     * @param event  the event who triggered the method
     */
    @FXML 
    public final void handlePress(final Event event) {
        final boolean state = btnTurn.isSelected();

        if (!state) {
            btnTurn.setText("OFF");
            btnTurn.setStyle("-fx-background-color: #F06249");
            ctrl.removeEffect();
        } else {
            btnTurn.setText("ON");
            btnTurn.setStyle("-fx-background-color: #6CF049");
        }

        getSliders().forEach(s -> s.setDisable(!state));
    }

    /**
     * Get the effect associated to the slider.
     * @param id  the id of the slider
     * @return an optional containing the effect, or empty if there isn't an effect associated to the id
     */
    private Optional<ALEffects> getEffect(final String id) {
        switch (id) {
        case "sldReverb":
            return Optional.of(ALEffects.REVERB);
        case "sldEcho":
            return Optional.of(ALEffects.ECHO);
        case "sldFlanger":
            return Optional.of(ALEffects.FLANGER);
        case "sldAutowah":
            return Optional.of(ALEffects.AUTOWAH);
        case "sldChorus":
            return Optional.of(ALEffects.CHORUS);
        default:
            return Optional.empty();
        }
    }

    /**
     * Get all the sliders.
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
            final Optional<ALEffects> effect = getEffect(slider.getId());
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
