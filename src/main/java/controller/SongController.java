package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import model.buffer.ALFormatException;
import model.buffer.Buffer;
import model.buffer.BufferCache;
import model.buffer.BufferFactory;
import model.buffer.BufferFactoryWithCache;
import model.source.hub.SourcesHub;
import view.SongView;

/**
 * Controller which communicate with model and SongView.
 *
 */
public class SongController implements ControllerApplication<SongView> {
    private static final String SONG_PATH = "/songs/";
    private final MainController mainCtr;
    private SongView ctrlView;
    private final BufferFactory factory = new BufferFactoryWithCache();

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
    public void setControllerView(final SongView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * Create a buffer from a file system path.
     * @param file  the file which will become the buffer
     */
    public final void addBufferFromPath(final String file) {
        try {
            factory.createBufferFromPath(file);
            ctrlView.updateComboBox(getBufferList());
        } catch (ALFormatException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            ctrlView.showMessage("Something went wrong during the creation of the buffer");
        }
    }

    /**
     * Create a buffer from a resource of the classpath.
     * @param file  the file which will become the buffer
     */
    public final void addBufferFromResource(final String file) {
        try {
            factory.createBufferFromResource(file);
        } catch (ALFormatException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            ctrlView.showMessage("Something went wrong during the creation of the buffer");
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
        Collections.sort(songs, (b1, b2) -> Integer.compare(b2.getID(), b1.getID())); 
        return songs;
    }

    /**
     * Play the specified buffer on all the sources.
     * @param bufferID  the id of the buffer
     */
    public void playSource(final int bufferID) {
        final SourcesHub sources = mainCtr.getEnvironmentController().getEnv().getSourcesHub();
        sources.generateAllSources(bufferID);
        sources.playAll();
        this.mainCtr.getSourceController().setDisableAddSource(true);
        this.mainCtr.getSpaceController().disableChange(true);
    }

    /**
     * Pause all the sources.
     */
    public void pauseSource() {
        final SourcesHub sources = mainCtr.getEnvironmentController().getEnv().getSourcesHub();
        sources.pauseAll();
    }

    /**
     * Stop all the sources.
     */
    public void stopSource() {
        final SourcesHub sources = mainCtr.getEnvironmentController().getEnv().getSourcesHub();
        sources.stopAll();
        this.mainCtr.getSourceController().setDisableAddSource(false);
        this.mainCtr.getSpaceController().disableChange(false);
    }

    /**
     * Get the selected buffer in the combobox. 
     * @return the id of the buffer
     */
    public int getSelectedID() {
        final var combobox = ctrlView.getCmbSongs();
        return combobox.getSelectionModel().getSelectedItem().getID();
    }

    /**
     * Import automatically all the wav in the resources path.
     * 
     * @throws IOException if there's a problem while reading the file
     */
    public void addStartSongs() throws IOException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(loader);
        Arrays.asList(resolver.getResources("classpath:songs/*.wav")).stream()
                .map(res -> SONG_PATH + res.getFilename())
                .forEach(this::addBufferFromResource);

        ctrlView.updateComboBox(getBufferList());
    }
}
