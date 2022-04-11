package model.source.hub;

import java.util.Set;

import model.effect.ALEffects;
import model.source.FRSource;
import model.utility.Vec3f;

/**
 * Sources Hub
 * Hub that gathers a group of Sources in it and that helps to manage this group as a whole.
 *
 */
public interface SourcesHub {

    /**
     * Gets a Set of Sources.
     * 
     * @return Set of All Sources
     */
    Set<FRSource> getAll();

    /**
     * Gets a Set of Vec3f.
     * 
     * @return Set of All Sources Positions
     */
    Set<Vec3f> getAllPositions();

    /**
     * Gets the Sources they are playing.
     * 
     * @return Set of Sources that are playing
     */
    Set<FRSource> getPlaying();

    /**
     * Gets the Source with the specified id.
     * 
     * @param id
     * @return Source with the specified id
     */
    FRSource getSource(Integer id);

    /**
     * Gets the Source with the specified position.
     * 
     * @param position
     * @return Source with the specified position
     */
    FRSource getSourceFromPos(Vec3f position);

    /**
     * Add the Source s to the SourcesHub.
     * 
     * @param s
     */
    void addSource(FRSource s);

    /**
     * Remove the Source s to the SourcesHub.
     * 
     * @param s
     */
    void removeSource(FRSource s);

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
    void applyFilter(ALEffects effect, float value);

    /**
     * Removes the filter from all sources.
     * 
     * @param effect
     */
    void removeFilter(ALEffects effect);

    /**
     * Delete all sources.
     */
    void deleteAll();

}
