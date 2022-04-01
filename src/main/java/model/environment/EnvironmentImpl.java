package model.environment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.effect.ALEffect;
import model.listener.Listener;
import model.source.hub.SourcesHub;
import model.utility.Vec3f;

public class EnvironmentImpl implements Environment {
    private final SourcesHub sourcesHub;
    private final Listener listener;
    private final Space space;
    private final Set<ALEffect> effect;

    public EnvironmentImpl(final SourcesHub sourcesHub, final Listener listener, final Space space) {
        super();
        this.sourcesHub = sourcesHub;
        this.listener = listener;
        this.space = space;
        this.effect = new HashSet<ALEffect>();
    }

//    public EnvironmentImpl() {
//        super();
//        final ListenerFactory listFac = new ListenerFactoryImpl();
//        //TODO this.sourcesHub = 
//        this.listener = listFac.createListener(AudioManager.getContext());
//        this.space = new SpaceImpl(10, 10);
//    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public Listener getListener() {
        return this.listener;
    }

    /**
    * 
    *{@inheritDoc}
    */
    @Override
    public Space getSpace() {
       return this.space;
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public SourcesHub getSourceHub() {
        return this.sourcesHub;
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void moveSourceWithID(final int id, final Vec3f pos) {
        if (this.space.isAvailable(pos)) {
            this.sourcesHub.getSource(id).setPosition(pos);
        }
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void moveSourceWithVec3f(final Vec3f oldPos, final Vec3f newPos) {
        if (this.space.isAvailable(newPos)) {
            this.sourcesHub.getSourceFromPos(oldPos).setPosition(newPos);
        }
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void setEffect(final ALEffect effect, final float level) {
        this.effect.add(effect);
        this.sourcesHub.applyFilter(effect, level);
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void removeEffect(final ALEffect effect) {
        this.effect.remove(effect);
        //this.sourcesHub.removeFilter(effect);
    }
    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Set<ALEffect> getEffectSet() {
        return Collections.unmodifiableSet(this.effect);
    }

}
