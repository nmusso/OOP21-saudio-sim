package model.audiomanager;

import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;

public final class AudioManager {
    private static Context context;
    private static ALCCapabilities alcCapabilities;
    private static ALCapabilities alCapabilities;

    private AudioManager() {
    }

    /**
     * Initialize a context.
     */
    public static void initContext() {
        final Device device = new Device();
        context = new Context(device);
        alcMakeContextCurrent(context.getId());
        alcCapabilities = ALC.createCapabilities(context.getDevice().getId());
        alCapabilities = AL.createCapabilities(alcCapabilities);
        AL.setCurrentProcess(alCapabilities);
    }

    /**
     * Getter for the current context.
     * @return the context
     */
    public static Context getContext() {
        return context;
    }
}
