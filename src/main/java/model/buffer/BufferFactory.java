package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface BufferFactory {
    /**
     * 
     * @param file The path of the file which will be associated to the buffer
     * @return The ID of the created buffer
     * @throws FileNotFoundException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    int createBuffer(String file) throws FileNotFoundException, UnsupportedAudioFileException, IOException;
}
