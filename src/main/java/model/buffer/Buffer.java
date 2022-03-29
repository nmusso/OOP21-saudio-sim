package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface Buffer {
    /**
     * Get the ID of the buffer.
     * @return ID of the buffer
     */
    int getID();

    /**
     * Get the path of the file.
     * @return The path of the file from which the buffer was generated
     */
    String getFile();

    /**
     * Generate the buffer from the path passed in the constructor.
     * @return The ID of the generated buffer
     * @throws FileNotFoundException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    int generateBuffer() throws FileNotFoundException, UnsupportedAudioFileException, IOException;
}
