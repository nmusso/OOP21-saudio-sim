package model.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface Buffer {
    /**
     * 
     * @return The ID of the buffer
     */
    int getBufferID();

    /**
     * 
     * @return The path of the file from which the buffer was generated
     */
    String getFile();

    /**
     * 
     * @param file The path of the audio file from which the buffer will be generated 
     * @return The ID of the generated buffer
     * @throws FileNotFoundException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    int generateBuffer(String file) throws FileNotFoundException, UnsupportedAudioFileException, IOException;
}
