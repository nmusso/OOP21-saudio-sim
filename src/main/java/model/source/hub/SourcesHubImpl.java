package model.source.hub;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.extension.effect.ALEffects;
import model.extension.effect.EffectImpl;
import model.source.FRSource;
import model.source.Source;
import model.utility.Vec3f;

/**
 * Hub that gathers a group of Sources in it and that helps to manage this group as a whole.
 *
 */
public class SourcesHubImpl implements SourcesHub {

    private final Set<FRSource> sources;
    private final EffectImpl effectManager;

    /**
     * Construct an empty SourceHubImpl.
     */
    public SourcesHubImpl() {
        this.sources = new HashSet<>();
        this.effectManager = new EffectImpl();
    }

    /**
     * Construct a SourceHubImpl from a given set.
     * @param sources the set of sources
     */
    public SourcesHubImpl(final Set<FRSource> sources) {
        this.sources = new HashSet<>();
        this.sources.addAll(sources);
        this.effectManager = new EffectImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<FRSource> getAll() {
        return Collections.unmodifiableSet(this.sources);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vec3f> getAllPositions() {
        return Collections.unmodifiableList(this.sources.stream().map(Source::getPosition).collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<FRSource> getPlaying() {
        return this.sources.stream().filter(Source::isPlaying).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<FRSource> getSource(final Integer id) {
        return this.sources.stream().filter(s -> s.getId().equals(id)).findAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<FRSource> getSourceFromPos(final Vec3f position) {
        return this.sources.stream().filter(s -> s.getPosition().equals(position)).findAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSource(final FRSource s) {
        this.sources.add(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSource(final FRSource s) {
        this.sources.remove(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playAll() {
        this.sources.forEach(Source::play);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseAll() {
        this.sources.forEach(Source::pause);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopAll() {
        this.sources.forEach(Source::stop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateAllSources(final int buffer) {
        this.sources.forEach(s -> s.generateSource(buffer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect(final ALEffects effect, final float value) {
        this.sources.forEach(s -> this.effectManager.apply(effect, s, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final ALEffects effect) {
        this.sources.forEach(s -> this.effectManager.remove(effect, s));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        this.sources.forEach(Source::delete);
        this.sources.clear();
    }
}
