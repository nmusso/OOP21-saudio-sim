package model.source;

import java.util.Set;

import model.extension.effect.EffectList;
import model.utility.Vec3f;

public interface SourcesHub {

    Set<Source> getAll();

    Set<Source> getPalying();

    void addSource(Source s);

    void removeSource(Source s);

    Source getSource(int id);

    Source getSourceByPos(Vec3f position);

    void playAll();

    void pauseAll();

    void stopAll();

    void applyFilter(EffectList effect);

}
