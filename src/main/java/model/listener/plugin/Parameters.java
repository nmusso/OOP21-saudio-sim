package model.listener.plugin;

import java.util.List;
import java.util.Optional;
import model.utility.Vec3f;
import model.utility.Pair;

/*TODO remove optional and initialize List to empty ArrayList*/
public class Parameters {
    private final Optional<List<Pair<ParameterType, Vec3f>>> vectorValues;
    private final Optional<List<Pair<ParameterType, Float>>> floatValues;

    public Parameters() {
        this.floatValues = Optional.empty();
        this.vectorValues = Optional.empty();
    }

    public Parameters(final ParameterType type, final float value) {
        floatValues = Optional.of(List.of(new Pair<>(type, value)));
        this.vectorValues = Optional.empty();
    }

    public Parameters(final List<Pair<ParameterType, Float>> floats, final List<Pair<ParameterType, Vec3f>> vectors) {
            this.vectorValues = Optional.of(vectors);
            this.floatValues = Optional.of(floats);
    }


    public Parameters(final ParameterType type, final Float param1) {
        this.vectorValues = Optional.empty(); 
        this.floatValues = Optional.of(List.of(new Pair<>(type, param1)));
    };


    /*TODO review value type not present or floatValues is Optional.empty()*/
    /**
     * 
     * @param type
     * @return
     */
    public Optional<Float> getFloatValue(final ParameterType type) {
        return this.floatValues.isPresent() ? this.floatValues.get().stream()
                                                                    .filter(x -> x.getX().equals(type))
                                                                    .findFirst()
                                                                    .map(p -> p.getY())
                                                                    .or(() -> Optional.empty())
                                            : Optional.empty();

    }


}
