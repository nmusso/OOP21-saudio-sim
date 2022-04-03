package model.listener.plugin;

import java.util.List;
import java.util.Optional;
import model.utility.Vec3f;
import model.utility.Pair;


public class Parameters {
    private final Optional<List<Pair<PluginType, Vec3f>>> vectorValues;
    private final Optional<List<Pair<PluginType, Float>>> floatValues;

    public Parameters(final PluginType type, final float value) {
        floatValues = Optional.of(List.of(new Pair<>(type, value)));
        this.vectorValues = Optional.empty();
    }

    public Parameters(final List<Pair<PluginType, Float>> floats, final List<Pair<PluginType, Vec3f>> vectors) {
            this.vectorValues = Optional.of(vectors);
            this.floatValues = Optional.of(floats);
    }


    public Parameters() {
        this.vectorValues = Optional.empty(); 
        this.floatValues = Optional.empty();
    };


    /*TODO reivew value type not present*/
    /**
     * 
     * @param type
     * @return
     */
    public Optional<Float> getFloatValue(final PluginType type) {
            return Optional.of(floatValues.get().stream()
                            .filter(x -> x.getX().equals(type))
                            .findFirst()
                            .get()
                            .getY());
    }



}
