package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.UnsupportedAudioFileException;

public final class BufferCache {

    private final Map<String, Buffer> buffers = new HashMap<>();

    private static class BufferHolder {
        private static final BufferCache INSTANCE = new BufferCache();
    }

    private BufferCache() { }

    /**
     * 
     * @return The instance of the singleton
     */
    public static BufferCache getInstance() {
        return BufferHolder.INSTANCE;
    }

    /**
     * 
     * @param path The path of the file
     * @param buffer The ID of the buffer associated to the path
     */
    public void addToCache(final String path, final Buffer buffer) {
        buffers.put(path, buffer);
    }

    /**
     * 
     * @param path The path of the file
     * @return The ID of the buffer associated to the path, got from cache or created by BufferImpl
     * @throws FileNotFoundException
     * @throws UnsupportedAudioFileException
     * @throws IOException
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
}
