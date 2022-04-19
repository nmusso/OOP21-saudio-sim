package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Interface for a factory of buffers.
 *
 */
public interface BufferFactory {
    /**
     * Create a new buffer if it was never generated from the path passed as
     * parameter, otherwise gets the ID from the cache.
     * 
     * @param file the path of the file which will be associated to the buffer
     * @return The ID of the created buffer
     * @throws FileNotFoundException         if file does not exists
     * @throws UnsupportedAudioFileException if the type of the file is not
     *                                       supported
     * @throws IOException                   if an error occur during read
     */
    Buffer createBufferFromPath(String file) throws FileNotFoundException, UnsupportedAudioFileException, IOException;

    /**
     * Create a new buffer if it was never generated from the resource path passed
     * as parameter, otherwise gets the ID from the cache.
     * 
     * @param file the path of the file which will be associated to the buffer
     * @return The ID of the created buffer
     * @throws FileNotFoundException         if file does not exists
     * @throws UnsupportedAudioFileException if the type of the file is not
     *                                       supported
     * @throws IOException                   if an error occur during read
     */
    Buffer createBufferFromResource(String file)
            throws FileNotFoundException, UnsupportedAudioFileException, IOException;
}
