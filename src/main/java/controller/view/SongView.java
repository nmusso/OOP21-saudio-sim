package controller.view;

import java.util.List;

import javafx.scene.control.ComboBox;
import model.buffer.Buffer;

/**
 * Interface for SongViewController.
 */
public interface SongView extends ControllerView {

    /**
     * Getter of the combobox with the buffers.
     * 
     * @return the combobox.
     */
    ComboBox<Buffer> getCmbSongs();

    /**
     * Update the items of the combobox.
     * 
     * @param list the buffer list
     */
    void updateComboBox(List<Buffer> list);
}
