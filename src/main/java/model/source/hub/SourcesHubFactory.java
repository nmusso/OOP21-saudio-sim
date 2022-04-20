package model.source.hub;

import java.util.Set;

import model.source.FRSource;

/**
 * SourcesHub Factory Intereface.
 * 
 */
public interface SourcesHubFactory {

    /**
     * Create a basic SourcesHub with no source.
     * 
     * @return SourcesHub
     */
    SourcesHub createSourcesHub();

    /**
     * Create a SourcesHub from a set of Sources.
     * 
     * @param sources
     * @return SourcesHub from Set of Sources
     */
    SourcesHub createSourcesHub(Set<FRSource> sources);
}
