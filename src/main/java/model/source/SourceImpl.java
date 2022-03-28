package model.source;

import model.utility.Vec3f;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.AL11.*;
import org.lwjgl.openal.*;
import static javax.sound.sampled.AudioSystem.*;
import java.io.*;
import javax.sound.sampled.*;
import java.nio.*;


public class SourceImpl implements Source {

    private final int id;
    private Vec3f position;
    private boolean isPlaying;

    public SourceImpl() {
        this.id = alGenSources();
        this.setPosition(new Vec3f(0.0f));
    }

    public SourceImpl(final Vec3f position) {
        this.id = alGenSources();
        this.setPosition(position);
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
    public final void setPosition(final Vec3f position) {
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
