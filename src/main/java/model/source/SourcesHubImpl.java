package model.source;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import model.extension.effect.ALEffect;
import model.extension.effect.EffectManager;
import model.utility.Vec3f;

public class SourcesHubImpl implements SourcesHub {

    private final Set<Source> sources;
    private final EffectManager effectManager;

    public SourcesHubImpl() {
        this.sources = new HashSet<>();
        this.effectManager = new EffectManager();
    }

    public SourcesHubImpl(final Set<Source> sources) {
        this.sources = sources;
        this.effectManager = new EffectManager();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<Source> getAll() {
        return Collections.unmodifiableSet(this.sources);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<Source> getPalying() {
        return this.sources.stream().filter(Source::isPlaying).collect(Collectors.toSet());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Source getSource(final Integer id) {
        return this.sources.stream().filter(s -> s.getId().equals(id)).findAny().get();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Source getSourceFromPos(final Vec3f position) {
        return this.sources.stream().filter(s -> s.getPosition().equals(position)).findAny().get();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addSource(final Source s) {
        this.sources.add(s);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeSource(final Source s) {
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
    public void applyFilter(final ALEffect effect, final float value) {
        this.sources.forEach(s -> this.effectManager.apply(effect, s, value));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeFilter(final ALEffect effect) {
        this.effectManager.remove(effect);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteAll() {
        this.sources.forEach(Source::delete);
    }

}
