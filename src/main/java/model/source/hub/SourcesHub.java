package model.source.hub;

import java.util.Set;

import model.effect.ALEffects;
import model.source.FreqRangeSource;
import model.source.Source;
import model.utility.Vec3f;

public interface SourcesHub {

    /**
     * Gets a Set of Sources.
     * 
     * @return Set of All Sources
     */
    Set<FreqRangeSource> getAll();

    /**
     * Gets the Sources they are playing.
     * 
     * @return Set of Sources that are playing
     */
    Set<FreqRangeSource> getPlaying();

    /**
     * Gets the Source with the specified id.
     * 
     * @param id
     * @return Source with the specified id
     */
    FreqRangeSource getSource(Integer id);

    /**
     * Gets the Source with the specified position.
     * 
     * @param position
     * @return Source with the specified position
     */
    FreqRangeSource getSourceFromPos(Vec3f position);

    /**
     * Add the Source s to the SourcesHub.
     * 
     * @param s
     */
    void addSource(FreqRangeSource s);

    /**
     * Remove the Source s to the SourcesHub.
     * 
     * @param s
     */
    void removeSource(FreqRangeSource s);

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
