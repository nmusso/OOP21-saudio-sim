package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import model.effect.ALEffects;

public class EqualizerController implements Initializable {

    @FXML private VBox container;
    @FXML private ToggleButton btnTurn;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        for (final Node node : container.getChildren()) {
            if (node instanceof Slider) {
                final Slider slider = (Slider) node;
                final Optional<ALEffects> effect = getEffect(slider.getId());
                if (effect.isPresent()) {
                    final var eff = effect.get();
                    slider.setMin(eff.getMinValue());
                    slider.setMax(eff.getMaxValue());
                    slider.valueProperty().addListener((obs, old, newValue) -> {
                        //MainController.getSources().foreach(s -> s.applyFilter(s, eff, newValue.floatValue()));
                    });
                }
            }
        }
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

    @FXML public final void handlePress(final Event event) {
        final boolean state = btnTurn.isSelected();

        if (state) {
            
        } else {
            
        }

        btnTurn.setSelected(!state);
    }
}
