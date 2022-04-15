package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import controller.view.SongControllerView;
import model.buffer.Buffer;
import model.buffer.BufferCache;
import model.buffer.BufferFactory;
import model.buffer.BufferFactoryImpl;
import model.source.hub.SourcesHub;

/**
 * Controller for the SongView which will communicate with model and his ViewController.
 *
 */
public class SongController implements ControllerApplication<SongControllerView> {
    private final MainController mainCtr;
    private SongControllerView ctrlView;
    private final BufferFactory factory = new BufferFactoryImpl();

    /**
     * Constructor of the SongController.
     * @param mainCtr  the main controller
     */
    public SongController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * Set the controller of the view.
     * @param controllerView  the controller view
     */
    @Override
    public void setControllerView(final SongControllerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * Create a buffer from the path.
     * @param file  the file which will become the buffer
     * @param isResource true if the resource was loaded from the resource path
     */
    public final void createBuffer(final String file, final boolean isResource) {
        try {
            if (isResource) {
                factory.createBufferFromResource(file);
            } else {
                factory.createBufferFromPath(file);
            }
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            ctrlView.showError("Something went wrong during the creation of the buffer");
        }
    }

    /**
     * Get all the buffers in the cache.
     * @return a list of buffers as string
     */
    public List<Buffer> getBufferList() {
        final var cache = BufferCache.INSTANCE.getCacheMap();
        final List<Buffer> songs = new ArrayList<>();

        cache.forEach((path, buffer) -> songs.add(buffer));

        return songs;
    }

    /**
     * Play the specified buffer on all the sources.
     * @param bufferID  the id of the buffer
     */
    public void playSource(final int bufferID) {
        final SourcesHub sources = mainCtr.getEnvironmentController().getEnv().getSourceHub();
        sources.generateAllSources(bufferID);
        sources.playAll();
        this.mainCtr.getSourceController().setDisableAddSource(true);
    }

    /**
     * Pause all the sources.
     */
    public void pauseSource() {
        final SourcesHub sources = mainCtr.getEnvironmentController().getEnv().getSourceHub();
        sources.pauseAll();
    }

    /**
     * Stop all the sources.
     */
    public void stopSource() {
        final SourcesHub sources = mainCtr.getEnvironmentController().getEnv().getSourceHub();
        sources.stopAll();
        this.mainCtr.getSourceController().setDisableAddSource(false);
    }

    /**
     * Get the selected buffer in the combobox. 
     * @return the id of the buffer
     */
    public int getSelectedID() {
        final var combobox = ctrlView.getCmbSongs();
        return combobox.getSelectionModel().getSelectedItem().getID();
    }
}
