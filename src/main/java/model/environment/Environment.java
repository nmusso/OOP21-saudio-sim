package model.environment;

import model.effect.ALEffects;
import model.listener.Listener;
import model.source.FRSource;
import model.source.hub.SourcesHub;
import model.space.Space;
import model.utility.Vec3f;

public interface Environment {

    /**
     *
     * @return the source Hub
     */
    SourcesHub getSourceHub();

    /**
     * 
     * @return listener
     */
    Listener getListener();

    /**
     *
     * @return space
     */
    Space getSpace();

    /**
     * add source to the source hub.
     * @param source to add
     */
    void addSourceToSourceHub(FRSource source);

    /**
     * add effect.
     * @param effect to add
     * @param level of "power" of effect
     */
    void addEffect(ALEffects effect, float level);

    /**
     * remove all effect.
     */
    void removeEffect();

    /**
     * remove source. 
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
