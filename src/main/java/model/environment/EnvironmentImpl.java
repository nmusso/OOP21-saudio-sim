package model.environment;

import model.extension.effect.ALEffects;
import model.listener.Listener;
import model.source.FRSource;
import model.source.hub.SourcesHub;
import model.space.Space;
import model.utility.Vec3f;

public class EnvironmentImpl implements Environment {
    private final SourcesHub sourcesHub;
    private final Listener listener;
    private final Space space;

    /**
     * Construct a new EnviromentImpl.
     * @param sourcesHub
     * @param listener
     * @param space
     */
    public EnvironmentImpl(final SourcesHub sourcesHub, final Listener listener, final Space space) {
        super();
        this.sourcesHub = sourcesHub;
        this.listener = listener;
        this.space = space;
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
        this.sourcesHub.applyEffect(effect, level);
    }

    /**
     * 
     *{@inheritDoc}
     */
    @Override
    public void removeEffect() {
        ALEffects.getValuesAsList().forEach(effect -> sourcesHub.removeEffect(effect));
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
        sourceToRemove.delete();
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
