package controller.view;

import javafx.scene.control.ComboBox;
import model.buffer.Buffer;

/**
 * Interface for SongViewController.
 */
public interface SongView extends ControllerView {

    /**
     * Getter of the combobox with the buffers.
     * @return the combobox.
     */
    ComboBox<Buffer> getCmbSongs();
}
