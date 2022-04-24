package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Implementation of the interface BufferFactory.
 *
 */
public class BufferFactoryWithCache implements BufferFactory {

    private final BufferCache cache = BufferCache.INSTANCE;

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer createBufferFromPath(final String file)
            throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        if (cache.getBuffer(file).isEmpty()) {
            final Buffer buffer = new PathBuffer(file);
            cache.addToCache(file, buffer);
            return buffer;
        }

        return cache.getBuffer(file).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer createBufferFromResource(final String file)
            throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        if (cache.getBuffer(file).isEmpty()) {
            final Buffer buffer = new ResourceBuffer(file);
            cache.addToCache(file, buffer);
            return buffer;
        }

        return cache.getBuffer(file).get();
    }
}
