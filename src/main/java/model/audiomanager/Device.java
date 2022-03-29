package model.audiomanager;

import static org.lwjgl.openal.ALC10.alcOpenDevice;
import static org.lwjgl.openal.ALC10.alcCloseDevice;

public class Device {

    private final long id;

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
