package model.effect;

import static org.lwjgl.openal.EXTEfx.AL_EFFECT_CHORUS;
import static org.lwjgl.openal.EXTEfx.AL_CHORUS_DELAY;
import static org.lwjgl.openal.EXTEfx.AL_CHORUS_MIN_DELAY;
import static org.lwjgl.openal.EXTEfx.AL_CHORUS_MAX_DELAY;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_DISTORTION;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_ECHO;
import static org.lwjgl.openal.EXTEfx.AL_ECHO_DELAY;
import static org.lwjgl.openal.EXTEfx.AL_ECHO_MIN_DELAY;
import static org.lwjgl.openal.EXTEfx.AL_ECHO_MAX_DELAY;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_FLANGER;
import static org.lwjgl.openal.EXTEfx.AL_FLANGER_DEPTH;
import static org.lwjgl.openal.EXTEfx.AL_FLANGER_MIN_DEPTH;
import static org.lwjgl.openal.EXTEfx.AL_FLANGER_MAX_DEPTH;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_PITCH_SHIFTER;
import static org.lwjgl.openal.EXTEfx.AL_PITCH_SHIFTER_COARSE_TUNE;
import static org.lwjgl.openal.EXTEfx.AL_PITCH_SHIFTER_MIN_COARSE_TUNE;
import static org.lwjgl.openal.EXTEfx.AL_PITCH_SHIFTER_MAX_COARSE_TUNE;
import static org.lwjgl.openal.EXTEfx.AL_EFFECT_REVERB;
import static org.lwjgl.openal.EXTEfx.AL_REVERB_ROOM_ROLLOFF_FACTOR;
import static org.lwjgl.openal.EXTEfx.AL_REVERB_MIN_ROOM_ROLLOFF_FACTOR;
import static org.lwjgl.openal.EXTEfx.AL_REVERB_MAX_ROOM_ROLLOFF_FACTOR;

/**
 * Effects for the equalizer.
 *
 */
public enum ALEffects {
    /**
     * Chorus effect.
     */
    CHORUS(AL_EFFECT_CHORUS, AL_CHORUS_DELAY, AL_CHORUS_MIN_DELAY, AL_CHORUS_MAX_DELAY), 
    /**
     * Echo effect.
     */
    ECHO(AL_EFFECT_ECHO, AL_ECHO_DELAY, AL_ECHO_MIN_DELAY, AL_ECHO_MAX_DELAY), 
    /**
     * Flanger effect.
     */
    FLANGER(AL_EFFECT_FLANGER, AL_FLANGER_DEPTH, AL_FLANGER_MIN_DEPTH, AL_FLANGER_MAX_DEPTH), 
    /**
     * Pitch effect.
     */
    PITCH(AL_EFFECT_PITCH_SHIFTER, AL_PITCH_SHIFTER_COARSE_TUNE, AL_PITCH_SHIFTER_MIN_COARSE_TUNE, AL_PITCH_SHIFTER_MAX_COARSE_TUNE), 
    /**
     * Reverb effect.
     */
    REVERB(AL_EFFECT_REVERB, AL_REVERB_ROOM_ROLLOFF_FACTOR, AL_REVERB_MIN_ROOM_ROLLOFF_FACTOR, AL_REVERB_MAX_ROOM_ROLLOFF_FACTOR);

    private final int effect;
    private final int attribute;
    private final float minValue;
    private final float maxValue;

    /**
     * Constructor for ALEffects.
     * @param effect  the id of the effect
     * @param attr  the id of the attribute
     * @param min  the min value of the effect
     * @param max  the max value of the effect
     */
    ALEffects(final int effect, final int attr, final float min, final float max) {
        this.effect = effect;
        this.attribute = attr;
        this.minValue = min;
        this.maxValue = max;
    }

    /**
     * Getter for the effect.
     * @return  the id of the effect
     */
    public int getEffect() {
        return effect;
    }

    /**
     * Getter for the attribute.
     * @return  the id of the attribute
     */
    public int getAttribute() {
        return attribute;
    }

    /**
     * Getter for the minimum value of the effect.
     * @return  the value
     */
    public float getMinValue() {
        return minValue;
    }

    /**
     * Getter for the maximum value of the effect.
     * @return  the value
     */
    public float getMaxValue() {
        return maxValue;
    }
}
