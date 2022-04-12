package model.listener.plugin.model;


import model.utility.Vec3f;

public class DropplerPlugin extends AbstractPlugin {
    private static final float DEFAULT_DROPPLER_LV = 1.0f;
    private Vec3f velocity;
    private float dropplerLv;
    private float lastDropplerLv;

    public DropplerPlugin() {
        this.velocity = new Vec3f(0.0f, 0.0f, 0.0f);
        this.dropplerLv = 1.0f;
    }

    /**
     * 
     */
    @Override
    public void restoreSettings() {
        this.setDropplerLv(this.lastDropplerLv);
    }

    /**
     * 
     */
    @Override
    public void saveSettings() {
        this.lastDropplerLv = this.dropplerLv;
        this.setDropplerLv(DEFAULT_DROPPLER_LV);
    }

    /**
     * 
     * @param vector
     */
    public void setVelocity(final Vec3f vector) {
        this.velocity = vector;
    }

    /**
     * 
     * @return velocity vector
     */
    public Vec3f getVelocity() {
        return this.velocity;
    }

    /**
     * 
     * @param x
     */
    public void setVelocityX(final Float x) {
        this.velocity = new Vec3f(x, 0.0f, 0.0f);
    }

    /**
     * 
     * @param y
     */
    public void setVelocityY(final Float y) {
        this.velocity = new Vec3f(0.0f, y, 0.0f);
    }

    /**
     * @param value 
     */
    public void setDropplerLv(final float value) {
        this.dropplerLv = value;
    }

    /**
     * @return droppler level
     */
    public float getDropplerLv() {
        return this.dropplerLv;
    }

}
