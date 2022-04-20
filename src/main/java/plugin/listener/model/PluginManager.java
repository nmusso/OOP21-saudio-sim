package plugin.listener.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class manage all plug-in related to a listener and allows
 * to do basic operation to manage the activity status of the plug-ins. 
 *
 */
public class PluginManager {
    private final Set<Plugin> plugins;

    public PluginManager() {
        this.plugins = new HashSet<>();
    }

    /**
     * 
     * @return Set of loaded plug-ins.
     */
    public Set<Plugin> getPlugins() {
        return Collections.unmodifiableSet(this.plugins);
    }

    /**
     * Add a plug-in in the manager.
     * @param plugin
     */
    public void addPlugin(final Plugin plugin) {
        this.plugins.add(plugin);
    }

    /**
     * Remove the plug-in from the manager.
     * @param pluginClassName
     */
    public void removePlugin(final String pluginClassName) {
        this.plugins.removeAll(this.plugins.stream()
                                            .filter(x -> x.getClassName().equals(pluginClassName))
                                            .collect(Collectors.toSet()));
    }

    /**
     * Enable all plug-in under management.
     */
    public void enableAll() {
        this.plugins.forEach(Plugin::enable);
    }

    /**
     * Disable all plug-in under management.
     */
    public void disableAll() {
        this.plugins.forEach(Plugin::disable);
    }

}
