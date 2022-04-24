package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public class BufferFactoryBasic implements BufferFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer createBufferFromPath(final String file)
            throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        return new PathBuffer(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Buffer createBufferFromResource(final String file)
            throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        return new ResourceBuffer(file);
    }

}
