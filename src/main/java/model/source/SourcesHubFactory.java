package model.source;

import java.util.Set;

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
     * @return SourcesHub from Set of Sources
     */
    SourcesHub createSourceHubFromSet(Set<Source> sources);
}
