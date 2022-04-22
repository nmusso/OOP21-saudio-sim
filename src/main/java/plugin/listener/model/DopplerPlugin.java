package plugin.listener.model;

import static org.lwjgl.openal.AL10.alListener3f;

import java.util.Optional;

import org.lwjgl.openal.AL10;

import model.utility.Vec3f;

/**
 * 
 * Doppler plug-in simulate the frequency change of a wave in relation to an observer
 * who is moving relative to the wave source.
 *
 */
public class DopplerPlugin extends AbstractPlugin {
    private static final float DEFAULT_DROPPLER_LV = 1.0f;
    private Vec3f velocity;
    private float dropplerLv;
    private Optional<Float> lastDropplerLv = Optional.empty();

    public DopplerPlugin() {
        this.velocity = new Vec3f(0.0f, 0.0f, 0.0f);
        this.dropplerLv = 1.0f;
        this.enable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void restoreSettings() {
        this.lastDropplerLv.ifPresent(x -> this.setDopplerLv(x));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettings() {
        this.lastDropplerLv = Optional.of(this.dropplerLv);
        this.setDopplerLv(DEFAULT_DROPPLER_LV);
        this.velocity = new Vec3f(0.0f, 0.0f, 0.0f);
        this.setVelocity(velocity);
    }

    /**
     * Set velocity vector of the listener.
     * @param vector
     */
    public void setVelocity(final Vec3f vector) {
        if (this.isEnabled()) {
            this.velocity = vector;
            alListener3f(AL10.AL_VELOCITY, this.velocity.getX(), this.velocity.getY(), this.velocity.getZ());
        }
    }

    /**
     * Get velocity vector.
     * @return velocity vector
     */
    public Vec3f getVelocity() {
        return this.velocity;
    }

    /**
     * Set how much the effect affects.
     * @param value 
     */
    public void setDopplerLv(final float value) {
        if (this.isEnabled()) {
            this.dropplerLv = value;
        }
    }

    /**
     * @return doppler level
     */
    public float getDopplerLv() {
        return this.dropplerLv;
    }

}
