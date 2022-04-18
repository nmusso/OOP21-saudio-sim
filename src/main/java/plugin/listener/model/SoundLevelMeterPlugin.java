package plugin.listener.model;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.listener.Listener;
import model.source.hub.SourcesHub;

public class SoundLevelMeterPlugin {
    private static final float SAFETY_DISTANCE = 3;
    private final SourcesHub sources;
    private final Listener listener;

    /*TODO nel controller quando le source si stoppano ripassare il sourceHUb*/
    public SoundLevelMeterPlugin(final SourcesHub sources, final Listener listener) {
        this.sources = sources;
        this.listener = listener;
    }

    /**
     * 
     * @return distance from the nearest source or -1 if there are no sources. 
     */
    public double sourceDistanceMin() {
        final Optional<Double> minDistance = this.sources.getAllPositions().stream()
                                                                .map(p -> Math.sqrt(Math.pow(p.getX() - this.listener.getPosition().getX(), 2)
                                                                                   + Math.pow(p.getY() - this.listener.getPosition().getY(), 2)))
                                                                .collect(Collectors.minBy(Comparator.naturalOrder()));

        Circle c = new Circle();
        c.setFill(Color.rgb(0, 0, 0));
        System.out.println(minDistance.get() + " - " +fun1(minDistance.get()));
        return minDistance.orElse(-1d);

    }
    
    public int fun1(final double x) {
        if (x >= SAFETY_DISTANCE ) return 510;
        if(x <= 0.0f) return 0;
        final float intervals = 510 / (SAFETY_DISTANCE * 10);
        return (int) (Math.round(x*10)*intervals);
    }

}
