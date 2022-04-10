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

public class SongController {
    private final MainController mainCtr;
    private SongControllerView ctrlView;
    private final BufferFactory factory = new BufferFactoryImpl();

    public SongController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    public SongController() {
        this.mainCtr = new MainController();
    }

    public final void createBuffer(final File file) {
        try {
            factory.createBufferFromPath(file.getAbsolutePath());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return
     */
    public List<String> getSongList() {
        final var cache = BufferCache.INSTANCE.getCacheMap();
        final List<String> songs = new ArrayList<>();

        cache.forEach((path, buffer) -> songs.add(buffer.toString()));

        return songs;
    }

    /**
     * 
     * @param controllerView
     */
    public void setControllerView(final SongControllerView controllerView) {
        ctrlView = controllerView;
    }

    /**
     * 
     * @param id
     */
    public void playSource(final int bufferID) {
        final Buffer buf = BufferCache.INSTANCE.getBufferFromID(bufferID);
        // TODO questo è il buffer che serve per le source, già testato il corretto funzionamento
    }

    /**
     * 
     */
    public void pauseSource() {
        // TODO
    }

    /**
     * 
     */
    public void stopSource() {
        // TODO
    }
}
