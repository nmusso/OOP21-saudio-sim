package model.environment;

import java.util.Set;

import model.effect.ALEffects;
import model.listener.Listener;
import model.source.Source;
import model.source.hub.SourcesHub;
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
     * add source to the source hub, if position is ok.
     * 
     * @param source to add
     */
    void addSourceToSourceHub(Source source);

    /**
     * remove source in that position.
     * 
     * @param source to add
     */
    void removeSourceFromSourceHubWithVec3f(Vec3f posSource);

    /**
     * remove source in that position.
     * 
     * @param source to add
     */
    void removeSourceFromSourceHubWithId(int idSource);

    /**
     * move the selected source with id to pos.
     * @param id of source
     * @param new pos
     */
    void moveSourceWithID(int id, Vec3f pos);

    /**
     * move the selected source with id to pos.
     * @param old pos of source to move
     * @param new pos
     */
    void moveSourceWithVec3f(Vec3f oldPos, Vec3f newPos);

    /**
     * add effect.
     * @param effect to add
     * @param level of "power" of effect
     */
    void addEffect(ALEffects effect, float level);

    /**
     * remove effect.
     * @param effect chosen to remove
     */
    void removeEffect(ALEffects effect);

    /**
     * 
     * @return a set of all activeEffect in environment
     */
    Set<ALEffects> getEffectSet();
}
