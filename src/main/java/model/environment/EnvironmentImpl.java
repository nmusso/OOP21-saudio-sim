package model.environment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.effect.ALEffects;
import model.listener.Listener;
import model.source.FRSource;
import model.source.hub.SourcesHub;
import model.space.Space;
import model.utility.Vec3f;

public class EnvironmentImpl implements Environment {
    private final SourcesHub sourcesHub;
    private final Listener listener;
    private final Space space;
    private final Set<ALEffects> effect;

    public EnvironmentImpl(final SourcesHub sourcesHub, final Listener listener, final Space space) {
        super();
        this.sourcesHub = sourcesHub;
        this.listener = listener;
        this.space = space;
        this.effect = new HashSet<>();
    }

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
    public void addEffect(final ALEffects effect, final float level) {
        this.effect.add(effect);
        this.sourcesHub.applyFilter(effect, level);
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void removeEffect(final ALEffects effect) {
        this.effect.remove(effect);
        this.sourcesHub.removeFilter(effect);
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public Set<ALEffects> getEffectSet() {
        return Collections.unmodifiableSet(this.effect);
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void addSourceToSourceHub(final FRSource source) {
        sourcesHub.addSource(source);
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void removeSourceFromSourceHub(final FRSource sourceToRemove) {
        this.sourcesHub.removeSource(sourceToRemove);
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void moveSource(final FRSource source, final Vec3f pos) {
        this.sourcesHub.getSource(source.getId()).setPosition(pos);
    }


}
