package model.environment;

import java.util.Optional;
import java.util.Set;

import model.listener.Listener;
import model.source.FRSource;
import model.space.Space;

/**
 * Interface for EnvironmentFactory.
 *
 */
public interface EnvironmentFactory {

    /**
     * create ad Environment with zero sources and default other param.
     * @return Environment
     */
     Environment createVoidEnvironment();

    /**
     * create ad Environment from json set.
     * @param json all string with preset
     * @param typeEnv exemple Cinema, Mono, Stereo ...
     * @return Environment
     */
    Environment createEnvironmentFromJson(String json, String typeEnv);

    /**
     * Create an Environment with param set.
     * @param sources set to add at sourcehub
     * @param listener created earlier
     * @param space specific
     * @return Environment
     */
    Environment createNEnvironment(Set<FRSource> sources, Listener listener, Optional<Space> space);
}
