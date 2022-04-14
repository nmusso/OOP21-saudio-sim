package model.buffer;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO8;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alGenBuffers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.lwjgl.BufferUtils;
import model.utility.ConvertToMono;

/**
 * Implementation of the interface Buffer, containing the id of the buffer and
 * the file who generated it.
 *
 */
public class BufferImpl implements Buffer {

    private int id;
    private final String file;

    /**
     * Construct a new BufferImpl.
     * 
     * @param file the path of the file which will be used for the buffer
     * @param isResource true if the resource was loaded from the resource path
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    public BufferImpl(final String file, final boolean isResource) throws UnsupportedAudioFileException, IOException {
        this.file = file;
        generateBuffer(isResource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getID() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFile() {
        return this.file;
    }

    /**
     * Generate the buffer from the path passed in the constructor.
     * 
     * @param isResource true if the resource was loaded from the resource path
     * @return the ID of the generated buffer
     * @throws FileNotFoundException         if file does not exists
     * @throws UnsupportedAudioFileException if the type of the file is not
     *                                       supported
     * @throws IOException                   if an error occur during read
     */
    private int generateBuffer(final boolean isResource) throws UnsupportedAudioFileException, IOException {
        final AudioInputStream stream = isResource ? getAudioInputStream(getClass().getResource(file)) : getAudioInputStream(loadFile(file));
        final var format = stream.getFormat();
        byte[] byteArray = new byte[stream.available()];
        stream.read(byteArray);

        if (format.getChannels() == 2) {
            byteArray = ConvertToMono.convert(byteArray, byteArray.length);
        }

        final int sampleSize = format.getSampleSizeInBits() == 8 ? AL_FORMAT_MONO8 : AL_FORMAT_MONO16;
        final ByteBuffer audioBuffer = getAudioBuffer(byteArray);

        this.id = alGenBuffers();
        alBufferData(this.id, sampleSize, audioBuffer, (int) format.getSampleRate());
        stream.close();

        return id;
    }

    /**
     * Return a flipper ByteBuffer from a byte array.
     * 
     * @param byteArray the byte array read from the AudioInputStream
     * @return the flipped byte array as ByteBuffer
     */
    private ByteBuffer getAudioBuffer(final byte[] byteArray) {
        final var buf = BufferUtils.createByteBuffer(byteArray.length);
        buf.put(byteArray);
        buf.flip();

        return buf;
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

    /**
     * Return a string with the id of the buffer and the name of the file.
     */
    @Override
    public String toString() {
        final String fileName = file.substring(file.lastIndexOf(System.getProperty("file.separator")) + 1);
        return this.id + ": " + fileName;
    }
}
