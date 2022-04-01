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

    private final int id;
    private final String file;

    public BufferImpl(final String file) {
        id = alGenBuffers();
        this.file = file;
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
     * {@inheritDoc}
     */
    @Override
    public int generateBuffer() throws UnsupportedAudioFileException, IOException {
        try (AudioInputStream stream = getAudioInputStream(loadFile(file))) {
            final var format = stream.getFormat();
            final int sampleSize = getALFormat(format);

            if (sampleSize == -1) {
                throw new ALFormatException("Can't handle format");
            }

            final byte[] byteArray = new byte[stream.available()];
            stream.read(byteArray);
            final ByteBuffer audioBuffer = getAudioBuffer(byteArray);

            alBufferData(id, sampleSize, audioBuffer, (int) format.getSampleRate());
            stream.close();
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }


        final BufferCache bc = BufferCache.getInstance();
        bc.addToCache(file, this);
        return id;
    }

    /**
     * 
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
     * 
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
            return -1;
        }
    }

    /**
     * 
     * @param file The path of the file
     * @return The loaded file
     * @throws FileNotFoundException
     */
    private File loadFile(final String file) throws FileNotFoundException {
        final File f = new File(file);

        if (!f.exists()) {
            throw new FileNotFoundException();
        }

        return f;
    }
}
