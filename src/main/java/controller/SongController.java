package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

import controller.view.SongControllerView;
import model.buffer.Buffer;
import model.buffer.BufferCache;
import model.buffer.BufferFactory;
import model.buffer.BufferFactoryImpl;

/**
 * Controller for the SongView which will communicate with model and his ViewController.
 *
 */
public class SongController {
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
    public void setControllerView(final SongControllerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * Create a buffer from the path.
     * @param file  the file which will become the buffer
     */
    public final void createBuffer(final File file) {
        try {
            factory.createBufferFromPath(file.getAbsolutePath());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all the buffers in the cache.
     * @return a list of buffers as string
     */
    public List<String> getBufferList() {
        final var cache = BufferCache.INSTANCE.getCacheMap();
        final List<String> songs = new ArrayList<>();

        cache.forEach((path, buffer) -> songs.add(buffer.toString()));

        return songs;
    }

    /**
     * Play the specified buffer on all the sources.
     * @param id  the id of the buffer
     */
    public void playSource(final int bufferID) {
        final var sources = mainCtr.getEnvironmentController().getEnv().getSourceHub();
        sources.generateAllSources(bufferID);
        sources.playAll();
    }

    /**
     * Pause all the sources.
     */
    public void pauseSource() {
        final var sources = mainCtr.getEnvironmentController().getEnv().getSourceHub();
        sources.pauseAll();
    }

    /**
     * Stop all the sources.
     */
    public void stopSource() {
        final var sources = mainCtr.getEnvironmentController().getEnv().getSourceHub();
        sources.stopAll();
    }
    
    private int getSelectedID() {
        final var combo = ctrlView.getCmbSongs();
        final String id = Character.toString(combo.getSelectionModel().getSelectedItem().charAt(0));
        return Integer.parseInt(id);
    }
}
