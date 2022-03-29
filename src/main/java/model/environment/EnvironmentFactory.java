package model.environment;

import java.util.List;
import java.util.Optional;

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
    Environment createNEnvironment(List<Source> sources, Listener listener, Optional<Space> space);
}
