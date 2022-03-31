package model.source;

import java.util.Set;

import model.extension.effect.ALEffect;
import model.utility.Vec3f;

public interface SourcesHub {

    /**
     * Gets a Set of Sources.
     * 
     * @return Set of All Sources
     */
    Set<Source> getAll();

    /**
     * Gets the Sources they are playing.
     * 
     * @return Set of Sources that are playing
     */
    Set<Source> getPalying();

    /**
     * Gets the Source with the specified id.
     * 
     * @param id
     * @return Source with the specified id
     */
    Source getSource(Integer id);

    /**
     * Gets the Source with the specified position.
     * 
     * @param position
     * @return Source with the specified position
     */
    Source getSourceFromPos(Vec3f position);

    /**
     * Add the Source s to the SourcesHub.
     * 
     * @param s
     */
    void addSource(Source s);

    /**
     * Remove the Source s to the SourcesHub.
     * 
     * @param s
     */
    void removeSource(Source s);

    /**
     * Play all sources.
     */
    void playAll();

    /**
     * Pause all sources.
     */
    void pauseAll();

    /**
     * Stop all sources.
     */
    void stopAll();

    /**
    * Apply the effect to all sources with the specified value.
    * 
    * @param effect
    * @param value
    */
    void applyFilter(ALEffect effect, float value);

    /**
     * Removes the filter from all sources.
     * 
     * @param effect
     */
    void removeFilter(ALEffect effect);

    /**
     * Delete all sources.
     */
    void deleteAll();

}
