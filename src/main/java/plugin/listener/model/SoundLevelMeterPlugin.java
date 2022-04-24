package plugin.listener.model;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import model.listener.Listener;
import model.source.hub.SourcesHub;
import model.utility.Vec3f;

/**
 * Visual indicator to display the sound intensity
 * relative to the position.
 */
public class SoundLevelMeterPlugin extends AbstractPlugin {
    private static final float SAFETY_DISTANCE = 3;
    private static final int MAX_VALUE_COLOR = 255;
    private Optional<SourcesHub> sources;
    private final Listener listener;

    /**
     * Construct a new SoundLevelMeterPlugin.
     * @param listener the listener
     */
    public SoundLevelMeterPlugin(final Listener listener) {
        this.listener = listener;
        this.sources = Optional.empty();
        this.enable();
    }

    /**
     * @return if the sourceHub is initialized.
     */
    public boolean sourceHubPresent() {
        return this.sources.isPresent();
    }

    /**
     * Set source hub instance.
     * @param sources the SourceHub.
     */
    public void setSourceHub(final SourcesHub sources) {
        this.sources = Optional.of(sources);
    }

    /**
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
     * Map the distance to a value in a defined range.
     * @param distance value of minimum distance.
     * @return mapped color.
     */
    private int getMappedColor(final double distance) {
        if (distance >= SAFETY_DISTANCE) {
            return MAX_VALUE_COLOR * 2;
        }
        if (distance <= 0.0f) {
            return 0;
        }
        final float intervals = (MAX_VALUE_COLOR * 2) / (SAFETY_DISTANCE * 10);
        return (int) (Math.round(distance * 10) * intervals);
    }

    /**
     * Calculates the color based on the distance from the source.
     * @return vector that represent the RGB color.
     */
    public Vec3f getRgbColor() {
        final var distanceMin = this.sourceDistanceMin();
        if (!this.isEnabled() || !this.sourceHubPresent() || Double.compare(distanceMin, -1d) == 0) {
            return new Vec3f(MAX_VALUE_COLOR, MAX_VALUE_COLOR, MAX_VALUE_COLOR);
        }

        final var mappedColor = this.getMappedColor(distanceMin);
        if (mappedColor <= MAX_VALUE_COLOR) {
            return new Vec3f(MAX_VALUE_COLOR, mappedColor, 0.0f); 
        }

        return new Vec3f(MAX_VALUE_COLOR - (mappedColor - MAX_VALUE_COLOR), MAX_VALUE_COLOR, 0.0f);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void restoreSettings() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettings() {

    }

}
