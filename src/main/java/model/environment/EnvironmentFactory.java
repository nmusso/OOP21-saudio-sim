package model.environment;

import java.util.Optional;
import java.util.Set;

import model.listener.Listener;
import model.source.Source;

public interface EnvironmentFactory {

    /**
    * create an Environment with an initial mono source.
    * @param The single-source
    * @param listener
    * @param space
    * @return Environment
    */
    Environment createMonoEnvironment(Source mono, Listener listener, Optional<Space> space);

    /**
    * create an Environment with an initial stereo source.
    * @param The sources left and right
    * @param listener
    * @param space
    * @return Environment
    */
    Environment createStereoEnvironment(Source left, Source right, Listener listener, Optional<Space> space);

    /**
    * create an Environment with multiple sources.
    * @param list of Sources
    * @param listener
    * @param space
    * @return Environment
    */
    Environment createNEnvironment(Set<Source> sources, Listener listener, Optional<Space> space);

    /**
    * create an Environment with specific set, like Cinema.
    * @return Environment
    */
    Environment createCinemaEnvironment();

    /**
    * create an Environment with specific set, like Concert.
    * @return Environment
    */
    Environment createConcertEnvironment();

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
