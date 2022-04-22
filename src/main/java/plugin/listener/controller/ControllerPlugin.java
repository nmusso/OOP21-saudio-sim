package plugin.listener.controller;

import plugin.listener.model.Plugin;

/**
 * The plug-in controller represents the entity that connects model to view
 * and manage the operations between them.
 *
 */
public interface ControllerPlugin {
    /**
     * 
     * @return the instances of the plug-in that this controller manages.
     */
    Plugin getPlugin();

    /**
     * This method destroys all objects related to this plug in. 
     */
    void removePlugin();
}
