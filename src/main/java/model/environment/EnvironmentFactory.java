package model.environment;

import java.util.Optional;
import java.util.Set;

import model.listener.Listener;
import model.source.FRSource;
import model.space.Space;

public interface EnvironmentFactory {

    /**
     * create ad Environment with zero sources and default other param.
     * @return Environment
     */
     Environment createVoidEnvironment();

    /**
    * create an Environment with an initial mono source.
    * @return Environment
    */
    Environment createMonoEnvironment();

    /**
    * create an Environment with an initial stereo source.
    * @return Environment
    */
    Environment createStereoEnvironment();

    /**
     * Create an Environment with param set.
     * @param sources
     * @param listener
     * @param space
     * @return Environment
     */
    Environment createNEnvironment(Set<FRSource> sources, Listener listener, Optional<Space> space);

    /**
    * create an Environment with specific set, like Cinema.
    * @return Environment
    */
    Environment createCinemaEnvironment();

    /**
    * create an Environment with specific set, like Stadium.
    * @return Environment
    */
    Environment createStadiumEnvironment();

    /**
    * create an Environment with specific set, like HIFI HOME STUDIO.
    * @return Environment
    */
    Environment createHIFIEnvironment();
}
