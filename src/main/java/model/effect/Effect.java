package model.effect;

//import java.util.function.Consumer;
import model.source.Source;

public interface Effect {
    void apply(ALEffect effect, Source source, float val);
    void remove(ALEffect effect, Source source);
}
