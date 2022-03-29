package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface BufferFactory {
    /**
     * Create a new buffer if it was never generated from the path passed as parameter, otherwise gets the ID from the cache.
     * @param file The path of the file which will be associated to the buffer
     * @return The ID of the created buffer
     * @throws FileNotFoundException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    int createBuffer(String file) throws FileNotFoundException, UnsupportedAudioFileException, IOException;
}
