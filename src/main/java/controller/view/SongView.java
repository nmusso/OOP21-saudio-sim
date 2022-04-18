package controller.view;

import java.io.IOException;

import javafx.scene.control.ComboBox;
import model.buffer.Buffer;

/**
 * Interface for SongViewController.
 */
public interface SongView extends ControllerView {

    /**
     * Update the items of the combobox.
     */
    void updateComboBox();

    /**
     * Import automatically all the wav in the resources path.
     * @throws IOException 
     */
    void addStartSongs() throws IOException;

    /**
     * Getter of the combobox with the buffers.
     * @return the combobox.
     */
    ComboBox<Buffer> getCmbSongs();
}
