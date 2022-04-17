package model.environment;

import model.extension.effect.ALEffects;
import model.listener.Listener;
import model.source.FRSource;
import model.source.hub.SourcesHub;
import model.space.Space;
import model.utility.Vec3f;

public interface Environment {

    /**
     * Get the sourceHub of env.
     * @return the source Hub
     */
    SourcesHub getSourceHub();

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
    void addSourceToSourceHub(FRSource source);

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
     * @param sourceToRemove
     */
    void removeSourceFromSourceHub(FRSource sourceToRemove);


    /**
     * move the selected source to pos.
     * @param  source
     * @param pos
     */
    void moveSource(FRSource source, Vec3f pos);
}
