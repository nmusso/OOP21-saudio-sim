package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface Buffer {
    /**
     * 
     * @return The ID of the buffer
     */
    int getID();

    /**
     * 
     * @return The path of the file from which the buffer was generated
     */
    String getFile();

    /**
     * 
     * @return The ID of the generated buffer
     * @throws FileNotFoundException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    int generateBuffer() throws FileNotFoundException, UnsupportedAudioFileException, IOException;
}
