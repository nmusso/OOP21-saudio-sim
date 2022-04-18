package plugin.listener.model;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.listener.Listener;
import model.listener.plugin.AbstractPlugin;
import model.source.hub.SourcesHub;
import model.utility.Vec3f;

public class SoundLevelMeterPlugin extends AbstractPlugin{
    private static final float SAFETY_DISTANCE = 3;
    private final SourcesHub sources;
    private final Listener listener;

    /*TODO nel controller quando le source si stoppano ripassare il sourceHUb*/
    public SoundLevelMeterPlugin(final SourcesHub sources, final Listener listener) {
        this.sources = sources;
        this.listener = listener;
        this.enable();
    }

    /**
     * 
     * @return distance from the nearest source or -1 if there are no sources. 
     */
    private double sourceDistanceMin() {
        final Optional<Double> minDistance = this.sources.getAllPositions().stream()
                                                                .map(p -> Math.sqrt(Math.pow(p.getX() - this.listener.getPosition().getX(), 2)
                                                                                   + Math.pow(p.getY() - this.listener.getPosition().getY(), 2)))
                                                                .collect(Collectors.minBy(Comparator.naturalOrder()));

        //System.out.println(minDistance.get() + " - " + getMappedColor(minDistance.get()));
        return minDistance.orElse(-1d);

    }

    /**
     * 
     * @param x
     * @return a.
     */
    private int getMappedColor(final double x) {
        if (x >= SAFETY_DISTANCE) {
            return 510;
        }
        if (x <= 0.0f) {
            return 0;
        }
        final float intervals = 510 / (SAFETY_DISTANCE * 10);
        return (int) (Math.round(x * 10) * intervals);
    }

    /**
     * 
     * @return a.
     */
    public Vec3f getRgbColor() {
        if(!this.isEnabled()) {
            return new Vec3f(255,255,255);
        }
        final var x = this.getMappedColor(this.sourceDistanceMin());
        if( x <= 255) {
            return new Vec3f(255, x, 0.0f); 
        }
        return new Vec3f(255 -(x - 255), 255, 0.0f);
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
