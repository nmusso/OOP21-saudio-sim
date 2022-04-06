package model.listener.plugin;

import java.util.Optional;


import model.utility.Vec3f;

public class DropplerPlugin extends AbstractPlugin {
    private final PluginType type = PluginType.DROPPLER_PLUGIN;
    private Vec3f velocity;
    private float dropplerLv;

    public DropplerPlugin() {
        this.velocity = new Vec3f(0.0f, 0.0f, 0.0f);
        this.dropplerLv = 1.0f;
    }

    @Override
    public void restoreSettings() {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveSettings() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PluginType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameters(final Parameters value) {
        this.dropplerLv = value.getFloatValue(ParameterType.DROPPLER_LV).orElse(1.0f);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Vec3f> getVectValue(final ParameterType type) {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Float> getFloatValue(final ParameterType type) {
        return type.equals(ParameterType.DROPPLER_LV) ? Optional.of(this.dropplerLv) : Optional.empty();
    }
    
    public Float getDropplerLv() {
        return this.dropplerLv;
    }

}
