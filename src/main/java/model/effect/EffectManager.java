package model.effect;

//import java.util.function.Consumer;
import model.source.Source;

public interface EffectManager {
    void apply(ALEffect effect, Source source, float val);
    void remove(ALEffect effect, Source source);
}
