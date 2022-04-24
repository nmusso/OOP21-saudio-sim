package model.environment;

import model.extension.effect.ALEffects;
import model.listener.Listener;
import model.source.FRSource;
import model.source.hub.SourcesHub;
import model.space.Space;
import model.utility.Vec3f;

/**
 * Interface for Environment.
 *
 */
public interface Environment {

    /**
     * Get the sourceHub of env.
     * @return the source Hub
     */
    SourcesHub getSourcesHub();

    /**
     * Get the listener of env.
     * @return listener
     */
    Listener getListener();

    /**
     * Get the space of env.
     * @return space
     */
    Space getSpace();

    /**
     * add source to the source hub.
     * @param source to add
     */
    void addSourceToSourcesHub(FRSource source);

    /**
     * add effect to all source.
     * @param effect to add
     * @param level of "power" of effect
     */
    void addEffect(ALEffects effect, float level);

    /**
     * remove all effect.
     */
    void removeEffect();

    /**
     * remove source from sourceHub. 
     * @param sourceToRemove from set
     */
    void removeSourceFromSourcesHub(FRSource sourceToRemove);


    /**
     * move the selected source to pos.
     * @param source selected
     * @param pos new vec3f to move
     */
    void moveSource(FRSource source, Vec3f pos);
}
