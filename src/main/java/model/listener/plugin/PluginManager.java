package model.listener.plugin;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PluginManager {
    private final Set<Plugin> plugins;

    public PluginManager() {
        this.plugins = new HashSet<>();
    }

    /**
     * 
     * @return ret.
     */
    public Set<Plugin> getPlugins() {
        return Collections.unmodifiableSet(this.plugins);
    }

    /**
     * 
     * @param plugin
     */
    public void addPlugin(final Plugin plugin) {
        this.plugins.add(plugin);
    }

    /**
     * 
     * @param pluginClassName
     */
    public void removePlugin(final String pluginClassName) {
        this.plugins.removeAll(this.plugins.stream()
                                            .filter(x -> x.getClassName().equals(pluginClassName))
                                            .collect(Collectors.toSet()));
    }

    /**
     * 
     */
    public void enableAll() {
        this.plugins.forEach(Plugin::enable);
    }

    /**
     * 
     */
    public void disableAll() {
        this.plugins.forEach(Plugin::disable);
    }

}
