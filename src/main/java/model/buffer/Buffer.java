package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface Buffer {
    /**
     * Get the ID of the buffer.
     * @return the ID of the buffer
     */
    int getID();

    /**
     * Get the path of the file.
     * @return the path of the file from which the buffer was generated
     */
    String getFile();

    /**
     * Generate the buffer from the path passed in the constructor.
     * @return the ID of the generated buffer
     * @throws FileNotFoundException if file does not exists
     * @throws UnsupportedAudioFileException if the type of the file is not supported
     * @throws IOException if an error occur during read
     */
    int generateBuffer() throws FileNotFoundException, UnsupportedAudioFileException, IOException;
}
