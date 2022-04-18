package model.buffer;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Buffer class created from the resources in the classpath.
 *
 */
public class ResourceBuffer extends AbstractBuffer {

    public ResourceBuffer(final String file) throws UnsupportedAudioFileException, IOException {
        super(file);
    }

    /**
    * {@inheritDoc}
     */
    @Override
    protected AudioInputStream getAudioStream()
            throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        return getAudioInputStream(getClass().getResource(super.getFile()));
    }

    /**
     * Return a string with the id of the buffer and the name of the file.
     */
    @Override
    public String toString() {
        final String file = super.getFile();
        final String fileName = file.substring(file.lastIndexOf('/') + 1);
        return super.getID() + ": " + fileName;
    }

}
