package model.environment;

import java.util.List;

import model.listener.SimpleListener;
import model.source.Source;

public interface EnvironmentFactory {

    /**
    * create an Environment with a unmodifiable mono source.
    * @param The single-source
    * @param listener
    * @param space
    * @return Environment
    */
    Environment createMonoEnvironment(Source mono, SimpleListener listener, Space space);

    /**
    * create an Environment with a unmodifiable stereo source.
    * @param The sources left and right
    * @param listener
    * @param space
    * @return Environment
    */
    Environment createStereoEnvironment(Source left, Source right, SimpleListener listener, Space space);

    /**
    * create an Environment with multiple sources.
    * @param list of Sources
    * @param listener
    * @param space
    * @return Environment
    */
    Environment createNEnvironment(List<Source> sources, SimpleListener listener, Space space);
    /**
     * create an Environment with default space.
     * @param list of Sources
     * @param listener
     * @return Environment
     */
     Environment createEnvironmentDefaultSpace(List<Source> sources, SimpleListener listener);
}
