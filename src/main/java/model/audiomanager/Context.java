package model.audiomanager;

import static org.lwjgl.openal.ALC10.*;

import java.nio.IntBuffer;
import java.util.Objects;

public class Context {
    private final Device device;
    private final long id;

    public Context(final Device device) {
        id = alcCreateContext(device.getId(), (IntBuffer) null);
        this.device = device;
    }
    /**
    *
    * @return id of Context
    */
    public long getId() {
        return this.id;
    }
    /**
    *
    * @return device
    */
    public Device getDevice() {
        return this.device;
    }
    /**
    * Destroy this context.
    *
    */
    public void destroy() {
        alcDestroyContext(id);
    }
    /**
    * Suspend this context.
    *
    */
    public void suspend() {
        alcSuspendContext(id);
    }

    /**
    *
    * @return the hashcode of context generate by ID
    */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
    *
    *@return boolean, true if the obj is equals to this context
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
