package model.buffer;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO8;
import static org.lwjgl.openal.AL10.AL_FORMAT_STEREO16;
import static org.lwjgl.openal.AL10.AL_FORMAT_STEREO8;
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

    private final int buffer;
    private final String file;

    public BufferImpl(final String file) {
        buffer = alGenBuffers();
        this.file = file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBufferID() {
        return this.buffer;
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
    public int generateBuffer(final String file) throws FileNotFoundException, UnsupportedAudioFileException, IOException {
        final AudioInputStream stream = getAudioInputStream(loadFile(file));
        final var format = stream.getFormat();
        final int sampleSize = getALFormat(format);

        if (sampleSize == -1) {
            throw new ALFormatException("can't handle format");
        }

        final byte[] byteArray = new byte[stream.available()];
        stream.read(byteArray);
        final ByteBuffer audioBuffer = getAudioBuffer(byteArray);
        stream.close();

        alBufferData(buffer, sampleSize, audioBuffer, (int) format.getSampleRate());

        return buffer;
    }

    private ByteBuffer getAudioBuffer(final byte[] byteArray) {
        final var buf = BufferUtils.createByteBuffer(byteArray.length);
        buf.put(byteArray);
        buf.flip();

        return buf;
    }

    private int getALFormat(final AudioFormat format) {
        switch (format.getChannels()) {
        case 1:
            return (format.getSampleSizeInBits() == 8) ? AL_FORMAT_MONO8 : AL_FORMAT_MONO16;
        case 2:
            return (format.getSampleSizeInBits() == 8) ? AL_FORMAT_STEREO8 : AL_FORMAT_STEREO16;
        default:
            return -1;
        }
    }

    private File loadFile(final String file) throws FileNotFoundException {
        final File f = new File(file);

        if (!f.exists()) {
            throw new FileNotFoundException();
        }

        return f;
    }
}
