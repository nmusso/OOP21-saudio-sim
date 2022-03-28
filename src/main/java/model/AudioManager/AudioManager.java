package model.AudioManager;

import javax.sound.sampled.*;
import org.lwjgl.openal.*;
import static javax.sound.sampled.AudioSystem.*;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class AudioManager {
    private Context context;
    private ALCCapabilities alcCapabilities;
    private ALCapabilities alCapabilities;
    public AudioManager() {
        initContext();
        alcCapabilities = ALC.createCapabilities(context.getDevice().getId());
        alCapabilities = AL.createCapabilities(alcCapabilities);
        AL.setCurrentProcess(this.alCapabilities);
    }

    private void initContext() {
        Device device = new Device();
        context = new Context(device);
        alcMakeContextCurrent(context.getId());
    }
}
