package model.audiomanager;

import static org.lwjgl.openal.ALC10.alcOpenDevice;
import static org.lwjgl.openal.ALC10.alcCloseDevice;

/**
 * Device class.
 *
 */
public class Device {

    private final long id;

    /**
     * Construct a new Device.
     */
    public Device() {
        id = alcOpenDevice((CharSequence) null);
    }

    /**
    *
    * @return id of device
    */
    public long getId() {
        return this.id;
    }

    /**
    * Close this device.
    *
    */
    public void close() {
        alcCloseDevice(id);
    }
}
