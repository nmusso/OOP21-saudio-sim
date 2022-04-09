package controller.view;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import controller.MainController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import model.effect.ALEffects;

public class EqualizerControllerView implements Initializable, ControllerView {

    @FXML private GridPane slidersPane;
    @FXML private ToggleButton btnTurn;
    private EqualizerControllerView ctrl;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        ctrl = new EqualizerControllerView();

        getSliders().forEach(slider -> {
            final Optional<ALEffects> effect = getEffect(slider.getId());
            if (effect.isPresent()) {
                final var eff = effect.get();
                slider.setMin(eff.getMinValue());
                slider.setMax(eff.getMaxValue());
                slider.valueProperty().addListener((obs, oldVal, newVal) -> {
                    // MainController.getSources().foreach(s -> s.applyFilter(s, eff, newVal));
                });
            }
        });
    }

    /**
     * Change color to the toggle button and enable/disable the sliders.
     * @param event  the event who triggered the method
     */
    @FXML public final void handlePress(final Event event) {
        final boolean state = btnTurn.isSelected();

        if (!state) {
            btnTurn.setText("OFF");
            btnTurn.setStyle("-fx-background-color: #F06249");
        } else {
            btnTurn.setText("ON");
            btnTurn.setStyle("-fx-background-color: #6CF049");
        }

        getSliders().forEach(s -> s.setDisable(!state));
    }

    /**
     * 
     * @return
     */
    public EqualizerControllerView getController() {
        return ctrl;
    }

    private Optional<ALEffects> getEffect(final String id) {
        switch (id) {
        case "sldReverb":
            return Optional.of(ALEffects.REVERB);
        case "sldDistortion":
            return Optional.of(ALEffects.DISTORTION);
        case "sldEcho":
            return Optional.of(ALEffects.ECHO);
        case "sldFlanger":
            return Optional.of(ALEffects.FLANGER);
        case "sldPitch":
            return Optional.of(ALEffects.PITCH);
        case "sldChorus":
            return Optional.of(ALEffects.CHORUS);
        default:
            return Optional.empty();
        }
    }

    private List<Slider> getSliders() {
        return slidersPane.getChildren().stream()
                .filter(node -> node instanceof Slider)
                .map(node -> (Slider) node)
                .collect(Collectors.toList());
    }

    @Override
    public void setControllerApplication(final MainController ctrMain) {
        // TODO Auto-generated method stub

    }
}
