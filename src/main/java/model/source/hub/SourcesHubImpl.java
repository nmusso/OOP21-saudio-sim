package model.source.hub;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import model.extension.effect.ALEffects;
import model.extension.effect.EffectImpl;
import model.source.FRSource;
import model.source.Source;
import model.utility.Vec3f;

public class SourcesHubImpl implements SourcesHub {

    private final Set<FRSource> sources;
    private final EffectImpl effectManager;

    public SourcesHubImpl() {
        this.sources = new HashSet<>();
        this.effectManager = new EffectImpl();
    }

    public SourcesHubImpl(final Set<FRSource> sources) {
        this.sources = sources;
        this.effectManager = new EffectImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<FRSource> getAll() {
        return Collections.unmodifiableSet(this.sources);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<Vec3f> getAllPositions() {
        return Collections.unmodifiableSet(this.sources.stream().map(Source::getPosition).collect(Collectors.toSet()));
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<FRSource> getPlaying() {
        return this.sources.stream().filter(Source::isPlaying).collect(Collectors.toSet());
    }

    /**
     * @inheritDoc
     */
    @Override
    public FRSource getSource(final Integer id) {
        return this.sources.stream().filter(s -> s.getId().equals(id)).findAny().get();
    }

    /**
     * @inheritDoc
     */
    @Override
    public FRSource getSourceFromPos(final Vec3f position) {
        return this.sources.stream().filter(s -> s.getPosition().equals(position)).findAny().get();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addSource(final FRSource s) {
        this.sources.add(s);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeSource(final FRSource s) {
        this.sources.remove(s);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void playAll() {
        this.sources.forEach(Source::play);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void pauseAll() {
        this.sources.forEach(Source::pause);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void stopAll() {
        this.sources.forEach(Source::stop);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void generateAllSources(final int buffer) {
        this.sources.forEach(s -> s.generateSource(buffer));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void applyFilter(final ALEffects effect, final float value) {
        this.sources.forEach(s -> this.effectManager.apply(effect, s, value));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeFilter(final ALEffects effect) {
        this.sources.forEach(s -> this.effectManager.remove(effect, s));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteAll() {
        this.sources.forEach(Source::delete);
    }

}
