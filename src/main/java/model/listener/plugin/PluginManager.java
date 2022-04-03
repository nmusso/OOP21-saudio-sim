package model.listener.plugin;



import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.listener.Listener;

public class PluginManager {

    private final Set<Plugin> plugins;
    private final Listener listener;

    public PluginManager(final Listener listener) {
        this.listener = listener;
        this.plugins = new HashSet<>();
    }

    /**
     * 
     * @return
     */
    Listener getListener() {
        return this.listener;
    }

    /**
     * 
     * @param plugins
     */
    void addPlugin(final Plugin...plugins) {
        this.plugins.addAll(Stream.of(plugins).collect(Collectors.toSet()));
    }

    /*TODO review bug plug-in not present*/
    /**
     * 
     * @param plugin
     * @return
     */
    Optional<Plugin> getPlugin(final PluginType plugin) {
        return this.plugins.stream().filter(p -> p.getType().equals(plugin))
                                  .findAny()
                                  .map(p -> Optional.of(p))
                                  .get();

    }
}
