package model.AudioManager;

import static org.lwjgl.openal.ALC10.*;

import java.nio.IntBuffer;
import java.util.Objects;

public class Context {
    private final Device device;
    private final long id;
    /**
    *
    * @inheritDoc
    *
    */
    public Context(final Device device) {
        id = alcCreateContext(device.getId(), (IntBuffer) null);
        this.device = device;
    }
    /**
    *
    * @inheritDoc
    *
    */
    public long getId() {
        return this.id;
    }
    /**
    *
    * @inheritDoc
    *
    */
    public Device getDevice() {
        return this.device;
    }
    /**
    *
    * @inheritDoc
    *
    */
    public void destroy() {
        alcDestroyContext(id);
    }
    /**
    *
    * @inheritDoc
    *
    */
    public void suspend() {
        alcSuspendContext(id);
    }

    /**
    *
    * @inheritDoc
    *
    */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
    *
    * @inheritDoc
    *
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

        final Context other = (Context) obj;
        return id == other.id;
    }
}
