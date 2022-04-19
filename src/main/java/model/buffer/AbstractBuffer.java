package model.buffer;

import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO8;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alGenBuffers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.lwjgl.BufferUtils;

import model.utility.ConvertToMono;

/**
 * AbstractBuffer class common for all type of buffers.
 */
public abstract class AbstractBuffer implements Buffer {

    private int id;
    private final String file;

    /**
     * Construct a new BufferImpl.
     * 
     * @param file the path of the file which will be used for the buffer
     * @throws IOException                   if an error occur during read
     * @throws UnsupportedAudioFileException if the type of the file is not
     *                                       supported
     */
    public AbstractBuffer(final String file) throws UnsupportedAudioFileException, IOException {
        this.file = file;
        generateBuffer();
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
     */
    private int generateBuffer() {
        try (AudioInputStream stream = getAudioStream()) {
            final var format = stream.getFormat();
            byte[] byteArray = new byte[stream.available()];
            stream.read(byteArray);

            if (format.getChannels() == 2) {
                byteArray = ConvertToMono.convert(byteArray, byteArray.length);
            }

            final int sampleSize = format.getSampleSizeInBits() == 8 ? AL_FORMAT_MONO8 : AL_FORMAT_MONO16;
            final ByteBuffer audioBuffer = getByteBuffer(byteArray);

            this.id = alGenBuffers();
            alBufferData(this.id, sampleSize, audioBuffer, (int) format.getSampleRate());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new ALFormatException("Error during generating buffer", e);
        }

        return id;
    }

    /**
     * Get the AudioInputStream associated to the file path.
     * 
     * @return the stream
     * @throws FileNotFoundException         if file does not exists
     * @throws UnsupportedAudioFileException if the type of the file is not
     *                                       supported
     * @throws IOException                   if an error occur during read
     */
    protected abstract AudioInputStream getAudioStream()
            throws FileNotFoundException, UnsupportedAudioFileException, IOException;

    /**
     * Return a flipper ByteBuffer from a byte array.
     * 
     * @param byteArray the byte array read from the AudioInputStream
     * @return the flipped byte array as ByteBuffer
     */
    private ByteBuffer getByteBuffer(final byte[] byteArray) {
        final var buf = BufferUtils.createByteBuffer(byteArray.length);
        buf.put(byteArray);
        buf.flip();

        return buf;
    }
}
