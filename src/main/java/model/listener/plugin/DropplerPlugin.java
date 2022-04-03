package model.listener.plugin;

import java.util.Optional;


import model.utility.Vec3f;

public class DropplerPlugin extends AbstractPlugin {
    private final PluginType type = PluginType.DROPPLER_PLUGIN;
    private Vec3f velocity;

    public DropplerPlugin() {
        this.velocity = new Vec3f(0.0f, 0.0f, 0.0f);
        // TODO Auto-generated constructor stub
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

    @Override
    public void setParameters(final Parameters value) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Vec3f> getVectValue(final PluginType type) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Float> getFloatValue(final PluginType type) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

}
