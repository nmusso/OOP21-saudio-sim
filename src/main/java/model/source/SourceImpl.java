package model.source;

import model.utility.Vec3f;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.AL11.*;
import org.lwjgl.openal.*;
import static javax.sound.sampled.AudioSystem.*;
import java.io.*;
import javax.sound.sampled.*;
import java.nio.*;
import java.util.Objects;


public class SourceImpl implements Source {

    private final Integer id;
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
    public Integer getId() {
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
    public void generateSource(final int buffer) {
        alSourcei(this.id, AL_BUFFER, buffer);
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

    /**
     * @inheritDoc
     */
    @Override
    public void delete() {
        alDeleteSources(this.id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, isPlaying, position);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SourceImpl other = (SourceImpl) obj;
        return Objects.equals(id, other.id) && isPlaying == other.isPlaying && Objects.equals(position, other.position);
    }

}
