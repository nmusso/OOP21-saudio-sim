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

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.lwjgl.BufferUtils;

public class BufferImpl implements Buffer {

    private int id;
    private final String file;

    public BufferImpl(final String file) {
        this.file = file;
        try {
            generateBuffer();
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new ALFormatException("Error while generating buffer", e);
        }
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
     * @return the ID of the generated buffer
     * @throws FileNotFoundException if file does not exists
     * @throws UnsupportedAudioFileException if the type of the file is not supported
     * @throws IOException if an error occur during read
     */
    private int generateBuffer() throws UnsupportedAudioFileException, IOException {
        try (AudioInputStream stream = getAudioInputStream(loadFile(file))) {
            final var format = stream.getFormat();
            final int sampleSize = getALFormat(format);

            final byte[] byteArray = new byte[stream.available()];
            stream.read(byteArray);
            final ByteBuffer audioBuffer = getAudioBuffer(byteArray);

            this.id = alGenBuffers();
            alBufferData(this.id, sampleSize, audioBuffer, (int) format.getSampleRate());
            stream.close();
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new ALFormatException("File of unsupported format", e);
        }

        return id;
    }

    /**
     * Return a flipper ByteBuffer from a byte array.
     * @param byteArray  the byte array read from the AudioInputStream
     * @return the flipped byte array as ByteBuffer
     */
    private ByteBuffer getAudioBuffer(final byte[] byteArray) {
        final var buf = BufferUtils.createByteBuffer(byteArray.length);
        buf.put(byteArray);
        buf.flip();

        return buf;
    }

    /**
     * Return if the format of the file is Mono 8 or 16 bit.
     * @param format  the property of the AudioInputStream, as AudioFormat
     * @return the format and bit of the file as AL constants
     */
    private int getALFormat(final AudioFormat format) {
        switch (format.getChannels()) {
        case 1:
            return (format.getSampleSizeInBits() == 8) ? AL_FORMAT_MONO8 : AL_FORMAT_MONO16;
        case 2:
            throw new ALFormatException("Stereo not supported");
        default:
            throw new ALFormatException("Can't handle format");
        }
    }

    /**
     * Load file from path.
     * @param file  the path of the file
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
