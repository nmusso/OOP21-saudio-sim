package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public class BufferFactoryImpl implements BufferFactory {

    private final BufferCache bc = BufferCache.INSTANCE;

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer createBufferFromPath(final String file) throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        return bc.getBuffer(file);
    }
}
