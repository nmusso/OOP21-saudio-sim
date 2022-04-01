package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.UnsupportedAudioFileException;
import static org.lwjgl.openal.AL10.alDeleteBuffers;

public final class BufferCache {

    private final Map<String, Buffer> buffers = new HashMap<>();

    private static class BufferHolder {
        private static final BufferCache INSTANCE = new BufferCache();
    }

    private BufferCache() { }

    /**
     * 
     * @return the instance of the singleton
     */
    public static BufferCache getInstance() {
        return BufferHolder.INSTANCE;
    }

    /**
     * 
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
        final Buffer buf;

        if (buffers.containsKey(path)) {
            buf = buffers.get(path); 
        } else {
            buf = new BufferImpl(path);
            buf.generateBuffer();
        }

        return buf;
    }

    /**
     * Clear the buffer cache.
     */
    public void emptyCache() {
        buffers.forEach((path, buf) -> {
            alDeleteBuffers(buf.getID());
            buffers.remove(path);
        });
    }
}
