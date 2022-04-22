package model.environment;

import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import model.audiomanager.AudioManager;
import model.listener.Listener;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.source.FRSource;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;
import model.source.SourceType;
import model.source.hub.SourcesHub;
import model.source.hub.SourcesHubFactory;
import model.source.hub.SourcesHubFactoryImpl;
import model.space.Space;
import model.space.SpaceFactory;
import model.space.SpaceFactoryImpl;
import model.utility.Pair;
import model.utility.Vec3f;

public class EnvironmentFactoryImpl implements EnvironmentFactory {

    private final SourcesHubFactory sourceHubFac = new SourcesHubFactoryImpl();
    private final SpaceFactory spaceFac = new SpaceFactoryImpl();
    private final SourceFactory sourceFac = new SourceFactoryImpl();
    private final ListenerFactory listenerFac = new ListenerFactoryImpl();

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public Environment createVoidEnvironment() {
        return new EnvironmentImpl(sourceHubFac.createSourcesHub(), listenerFac.createListenerWithPos(AudioManager.getContext(), new Vec3f(0f)), spaceFac.createDefaultSpace());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment createNEnvironment(final Set<FRSource> sources, final Listener listener, final Optional<Space> space) {
       return new EnvironmentImpl(sourceHubFac.createSourcesHubFromSet(sources), listener, space.isPresent() ? space.get() : spaceFac.createDefaultSpace());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment createEnvironmentFromJson(final String json, final String typeEnv) {
        final SourcesHub sh = sourceHubFac.createSourcesHub();
        final Vec3f posListener;
        final Pair<Float, Float> sizeSpace;
        final JSONObject obj = new JSONObject(json);
        final JSONArray list = obj.getJSONArray(typeEnv);
        int i = 0;
        for (; i < list.length() - 2; i++) {
            final var x = list.getJSONObject(i);
            sh.addSource(sourceFac.createFRSourceWithPosition(new Vec3f(x.getFloat("x"),  x.getFloat("y"), 0f), SourceType.values()[x.getInt("t")]));
        }

        final JSONObject spaceRow = list.getJSONObject(list.length() - 2);
        sizeSpace = new Pair<>(spaceRow.getFloat("x"), spaceRow.getFloat("y"));
        final JSONObject listenerRow = list.getJSONObject(list.length() - 1);
        posListener = new Vec3f(listenerRow.getFloat("x"), listenerRow.getFloat("y"), 0f);
        return new EnvironmentImpl(sh, listenerFac.createListenerWithPos(AudioManager.getContext(), posListener), spaceFac.createCustomizedSpace(sizeSpace.getX(), sizeSpace.getY()));
    }

}
