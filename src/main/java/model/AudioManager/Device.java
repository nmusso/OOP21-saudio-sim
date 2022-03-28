package model.AudioManager;

import static org.lwjgl.openal.ALC10.*;

public class Device {

    private final long id;
    /**
    *
    * @inheritDoc
    *
    */
    public Device() {
        id = alcOpenDevice((java.lang.CharSequence) null);
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
    public void close() {
        alcCloseDevice(id);
    }
}
