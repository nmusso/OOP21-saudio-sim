package model.effect;

import static org.lwjgl.openal.AL11.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTEfx.*;

public enum ALEffects {
    /**
     * Chorus effect.
     */
    CHORUS(0, AL_EFFECT_CHORUS, AL_CHORUS_DEPTH, AL_CHORUS_DEFAULT_DELAY, AL_CHORUS_MIN_DELAY, AL_CHORUS_MAX_DELAY), 
    /**
     * Distortion effect.
     */
    DISTORTION(1, AL_EFFECT_DISTORTION, AL_DISTORTION_EDGE, AL_DISTORTION_DEFAULT_EDGE, AL_DISTORTION_MIN_EDGE, AL_DISTORTION_MAX_EDGE), 
    /**
     * Echo effect.
     */
    ECHO(2, AL_EFFECT_ECHO, AL_ECHO_DELAY, AL_ECHO_DEFAULT_DELAY, AL_ECHO_MIN_DELAY, AL_ECHO_MAX_DELAY), 
    /**
     * Flanger effect.
     */
    FLANGER(3, AL_EFFECT_FLANGER, AL_FLANGER_DEPTH, AL_FLANGER_DEFAULT_DEPTH, AL_FLANGER_MAX_DEPTH, AL_FLANGER_MIN_DEPTH), 
    /**
     * Pitch effect.
     */
    PITCH(4, AL_EFFECT_PITCH_SHIFTER, AL_PITCH_SHIFTER_COARSE_TUNE, AL_PITCH_SHIFTER_DEFAULT_COARSE_TUNE, AL_PITCH_SHIFTER_MIN_COARSE_TUNE, AL_PITCH_SHIFTER_MAX_COARSE_TUNE), 
    /**
     * Reverb effect.
     */
    REVERB(5, AL_EFFECT_REVERB, AL_REVERB_ROOM_ROLLOFF_FACTOR, AL_REVERB_DEFAULT_ROOM_ROLLOFF_FACTOR, AL_REVERB_MIN_ROOM_ROLLOFF_FACTOR, AL_REVERB_MAX_ROOM_ROLLOFF_FACTOR);

    private final int num; 
    private final int effect;
    private final int attribute;
    private final float defaultValue;
    private final float minValue;
    private final float maxValue;

    ALEffects(final int num, final int effect, final int attr, final float def, final float min, final float max) {
        this.num = num; 
        this.effect = effect;
        this.attribute = attr;
        this.defaultValue = def;
        this.minValue = min;
        this.maxValue = max;
    }

    public int getNumber() {
        return num;
    }

    public int getEffect() {
        return effect;
    }

    public int getAttribute() {
        return attribute;
    }

    public float getDefaultValue() {
        return defaultValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }
}
