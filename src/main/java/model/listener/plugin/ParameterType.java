package model.listener.plugin;

public enum ParameterType {
    /**
     * DropplerPlugin parameter available.
     */
    VELOCITY("Velocity vector", PluginType.DROPPLER_PLUGIN), DROPPLER_LV("Droppler effect level", PluginType.DROPPLER_PLUGIN),

    /**
     * SoundMeterPlugin parameter available.
     */
    DB_LV("Decibel Level", PluginType.SOUNDMETER_PLUGIN);

    private final String label;
    private final PluginType plugin;

    ParameterType(final String label, final PluginType plugin) {
        this.label = label;
        this.plugin = plugin;
    }

    public String getLabel() {
        return this.label;
    }

    public PluginType getPlugin() {
        return this.plugin;
    }



}
