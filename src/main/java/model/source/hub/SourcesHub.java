package model.source.hub;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.extension.effect.ALEffects;
import model.source.FRSource;
import model.utility.Vec3f;

/**
 * Sources Hub Interface.
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
    List<Vec3f> getAllPositions();

    /**
     * Gets the Sources they are playing.
     * 
     * @return Set of Sources that are playing
     */
    Set<FRSource> getPlaying();

    /**
     * Gets the Source with the specified id.
     * 
     * @param id the id of the source
     * @return Optional of Source with the specified id
     */
    Optional<FRSource> getSource(Integer id);

    /**
     * Gets the Source with the specified position.
     * 
     * @param position the position of the source
     * @return Optional of Source with the specified position
     */
    Optional<FRSource> getSourceFromPos(Vec3f position);

    /**
     * Add the Source s to the SourcesHub.
     * 
     * @param s the source
     */
    void addSource(FRSource s);

    /**
     * Remove the Source s to the SourcesHub.
     * 
     * @param s the source
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
     * Stop all sources.
     * 
     * @param buffer the id of the buffer
     */
    void generateAllSources(int buffer);

    /**
    * Apply the effect to all sources with the specified value.
    * 
    * @param effect the effect to be applied
    * @param value the level of the effect
    */
    void applyEffect(ALEffects effect, float value);

    /**
     * Removes the filter from all sources.
     * 
     * @param effect the effect to be removed
     */
    void removeEffect(ALEffects effect);

    /**
     * Delete all sources.
     */
    void deleteAll();
}
