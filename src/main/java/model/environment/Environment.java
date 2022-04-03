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
    * @return TODO
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
    * @param source to add
    */
    void addSourceToSourceHub(Source source);

    /**
     * remove source in that position.
     * @param source to add
     */
     void removeSourceFromSourceHubWithVec3f(Vec3f posSource);

     /**
      * remove source in that position.
      * @param source to add
      */
      void removeSourceFromSourceHubWithId(int idSource);

    /**
    * move the selected source with id to pos.
    * 
    */
    void moveSourceWithID(int id, Vec3f pos);

    /**
     * move the selected source with id to pos.
     * 
     */
     void moveSourceWithVec3f(Vec3f oldPos, Vec3f newPos);

     /**
      * TODO.
      * 
      */
     void setEffect(ALEffects effect, float level);

     /**
      * TODO.
      * 
      */
     void removeEffect(ALEffects effect);

     /**
      * TODO.
      * 
      */
     Set<ALEffects> getEffectSet();
}
