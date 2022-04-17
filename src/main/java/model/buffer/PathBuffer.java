package model.buffer;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Buffer class created from a path in the file system.
 *
 */
public class PathBuffer extends AbstractBuffer {

    public PathBuffer(final String file) throws UnsupportedAudioFileException, IOException {
        super(file);
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     * @throws FileNotFoundException 
     */
    @Override
    protected AudioInputStream getAudioStream() throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        return getAudioInputStream(loadFile(super.getFile()));
    }

    /**
     * Load file from path.
     * 
     * @param file the path of the file
     * @return the loaded file
     * @throws FileNotFoundException if file does not exists
     */
    private File loadFile(final String file) throws FileNotFoundException {
        final File f = new File(file);

        if (!f.exists()) {
            throw new FileNotFoundException();
        }

        return f;
    }

}
