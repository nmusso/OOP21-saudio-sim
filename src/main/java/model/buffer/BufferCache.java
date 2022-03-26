package model.buffer;

import java.util.HashMap;
import java.util.Map;

public final class BufferCache {

    private final Map<String, BufferImpl> buffers = new HashMap<>();

    private static class BufferHolder {
        private static final BufferCache INSTANCE = new BufferCache();
    }

    private BufferCache() { }

    public static BufferCache getInstance() {
        return BufferHolder.INSTANCE;
    }

    public void addToCache(final String path, final BufferImpl buffer) {
        buffers.put(path, buffer);
    }

    public boolean contains(final String path) {
        return buffers.containsKey(path);
    }

    public BufferImpl getBuffer(final String path) {
        return buffers.get(path);
    }
}
