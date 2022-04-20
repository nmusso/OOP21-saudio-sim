package plugin.listener.model;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import model.listener.Listener;
import model.listener.plugin.AbstractPlugin;
import model.source.hub.SourcesHub;
import model.utility.Vec3f;

public class SoundLevelMeterPlugin extends AbstractPlugin {
    private static final float SAFETY_DISTANCE = 3;
    private static final int MAX_BIT_COLOR = 255;
    private Optional<SourcesHub> sources;
    private final Listener listener;

    public SoundLevelMeterPlugin(final Listener listener) {
        this.listener = listener;
        this.sources = Optional.empty();
        this.enable();
    }

    /**
     * 
     * @return if the sourceHub is initializate.
     */
    public boolean sourceHubPresent() {
        return this.sources.isPresent();
    }

    /**
     * 
     * @param sources
     */
    public void setSourceHub(final SourcesHub sources) {
        this.sources = Optional.of(sources);
    }

    /**
     * 
     * @return distance from the nearest source or -1 if there are no sources. 
     */
    private double sourceDistanceMin() {
        final Optional<Double> minDistance = this.sourceHubPresent() ? this.sources.get().getAll().stream()
                                                                .filter(s -> s.isPlaying())
                                                                .map(p -> Math.sqrt(Math.pow(p.getPosition().getX() - this.listener.getPosition().getX(), 2)
                                                                                   + Math.pow(p.getPosition().getY() - this.listener.getPosition().getY(), 2)))
                                                                .collect(Collectors.minBy(Comparator.naturalOrder()))
                                                                : Optional.empty();
        return minDistance.orElse(-1d);
    }

    /**
     * 
     * @param x
     * @return a.
     */
    private int getMappedColor(final double x) {
        if (x >= SAFETY_DISTANCE) {
            return MAX_BIT_COLOR * 2;
        }
        if (x <= 0.0f) {
            return 0;
        }
        final float intervals = (MAX_BIT_COLOR * 2) / (SAFETY_DISTANCE * 10);
        return (int) (Math.round(x * 10) * intervals);
    }

    /**
     * 
     * @return a.
     */
    public Vec3f getRgbColor() {
        final var distanceMin = this.sourceDistanceMin();
        if (!this.isEnabled() || !this.sourceHubPresent() || Double.compare(distanceMin, -1d) == 0) {
            return new Vec3f(MAX_BIT_COLOR, MAX_BIT_COLOR, MAX_BIT_COLOR);
        }

        final var mappedColor = this.getMappedColor(distanceMin);
        if (mappedColor <= MAX_BIT_COLOR) {
            return new Vec3f(MAX_BIT_COLOR, mappedColor, 0.0f); 
        }

        return new Vec3f(MAX_BIT_COLOR - (mappedColor - MAX_BIT_COLOR), MAX_BIT_COLOR, 0.0f);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "SoundLevelMeterPlugin";
    }

    @Override
    public void restoreSettings() {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveSettings() {
        // TODO Auto-generated method stub

    }

}
