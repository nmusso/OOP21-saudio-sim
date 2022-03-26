package model.source;

import model.utility.Vec3f;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.AL11.*;
import org.lwjgl.openal.*;
import static org.lwjgl.openal.ALC10.*;
import static javax.sound.sampled.AudioSystem.*;
import java.io.*;
import javax.sound.sampled.*;
import org.lwjgl.BufferUtils;
import java.nio.*;
import org.lwjgl.openal.EXTEfx; 
import static org.lwjgl.openal.EXTEfx.*; 
import org.lwjgl.openal.EXTEfx;


public class SourceImpl implements Source {

    private int id;
    private Vec3f position;
    private boolean isPlaying;

    public SourceImpl(final int id, final Vec3f position) {
        this.id = id;
        this.position = position;
        this.isPlaying = false;
    }

    public SourceImpl(final int id) {
        this.id = id;
        this.isPlaying = false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void play() {
        isPlaying = true;
        alSourcePlay(this.id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void pause() {
        isPlaying = false;
        alSourcePause(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void stop() {
        isPlaying = false;
        alSourceStop(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Source generateSource(final int  buffer) {
        alSourcei(this.id, AL_BUFFER, buffer);
        return this;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setPosition(final Vec3f position) {
        this.position = position;
        alSource3f(this.id, AL_POSITION, this.position.getX(), this.position.getY(), this.position.getZ());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Vec3f getPosition() {
        return this.position;
    }

}
