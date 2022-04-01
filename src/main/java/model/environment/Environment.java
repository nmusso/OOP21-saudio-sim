package model.environment;

import java.util.Set;

import model.effect.ALEffect;
import model.listener.Listener;
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
     void setEffect(ALEffect effect, float level);

     /**
      * TODO.
      * 
      */
     void removeEffect(ALEffect effect);

     /**
      * TODO.
      * 
      */
     Set<ALEffect> getEffectSet();
}
