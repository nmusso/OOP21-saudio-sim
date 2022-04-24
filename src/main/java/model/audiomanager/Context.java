package model.audiomanager;

import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.openal.ALC10.alcSuspendContext;
import static org.lwjgl.openal.EXTEfx.ALC_MAX_AUXILIARY_SENDS;

import java.nio.IntBuffer;
import java.util.Objects;

import org.lwjgl.BufferUtils;

import model.extension.effect.ALEffects;

/**
 * Context class.
 *
 */
public class Context {
    private final Device device;
    private final long id;

    /**
     * Construct a Context.
     * @param device the device associated to the context
     */
    public Context(final Device device) {
        final IntBuffer contextAttribList = BufferUtils.createIntBuffer(3);
        contextAttribList.put(ALC_MAX_AUXILIARY_SENDS);
        contextAttribList.put(ALEffects.values().length);

        contextAttribList.put(0);
        contextAttribList.flip();

        id = alcCreateContext(device.getId(), contextAttribList);
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
