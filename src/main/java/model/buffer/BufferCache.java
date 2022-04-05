package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.UnsupportedAudioFileException;
import static org.lwjgl.openal.AL10.alDeleteBuffers;

public enum BufferCache {
    /**
     * BufferCache instance.
     */
    INSTANCE;
    private final Map<String, Buffer> buffers = new HashMap<>();

    /**
     * Add the buffer to the cache.
     * @param path  the path of the file
     * @param buffer  the ID of the buffer associated to the path
     */
    public void addToCache(final String path, final Buffer buffer) {
        buffers.put(path, buffer);
    }

    /**
     * 
     * @param path  the path of the file
     * @return the ID of the buffer associated to the path, got from cache or created by BufferImpl
     * @throws FileNotFoundException if file does not exists
     * @throws UnsupportedAudioFileException if the type of the file is not supported
     * @throws IOException if an error occur during read
     */
    public Buffer getBuffer(final String path) throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        if (!buffers.containsKey(path)) {
            final Buffer buf = new BufferImpl(path);
            buffers.put(path, buf);
        }

        return buffers.get(path);
    }

    /**
     * Clear the buffer cache.
     */
    public void emptyCache() {
        buffers.forEach((path, buf) -> alDeleteBuffers(buf.getID()));
        buffers.clear();
    }
}
