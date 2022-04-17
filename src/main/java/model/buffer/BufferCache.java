package model.buffer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.lwjgl.openal.AL10.alDeleteBuffers;

/**
 * Cache containing all the generated buffers.
 *
 */
public enum BufferCache {
    /**
     * BufferCache instance.
     */
    INSTANCE;

    private final Map<String, Buffer> buffers = new HashMap<>();

    /**
     * Add the buffer to the cache.
     * 
     * @param path   the path of the file
     * @param buffer the ID of the buffer associated to the path
     */
    public void addToCache(final String path, final Buffer buffer) {
        buffers.put(path, buffer);
    }

    /**
     * Get the buffer from the cache based on the path, created if not exists.
     * 
     * @param path the path of the file
     * @return the buffer associated to the path, got from cache or created
     */
    public Optional<Buffer> getBuffer(final String path) {
        return buffers.containsKey(path) ? Optional.of(buffers.get(path)) : Optional.empty();
    }

    /**
     * Get the buffer associated at the id.
     * 
     * @param id the id of the buffer
     * @return the buffer
     */
    public Buffer getBufferFromID(final int id) {
        return buffers.entrySet().stream()
                .filter(e -> e.getValue().getID() == id)
                .map(e -> e.getValue())
                .collect(Collectors.toList()).get(0);
    }

    /**
     * Getter of the cache map.
     * 
     * @return an unmodifiable copy of the map
     */
    public Map<String, Buffer> getCacheMap() {
        return Collections.unmodifiableMap(buffers);
    }

    /**
     * Clear the buffer cache.
     */
    public void emptyCache() {
        buffers.forEach((path, buf) -> alDeleteBuffers(buf.getID()));
        buffers.clear();
    }
}
