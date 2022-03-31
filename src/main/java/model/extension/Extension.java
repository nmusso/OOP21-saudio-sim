package model.extension;

import model.extension.effect.ALEffect;
//import java.util.function.Consumer;
import model.source.Source;

public interface Extension {
    void apply(ALEffect effect, Source source, float val);
    void remove(ALEffect effect);
}
