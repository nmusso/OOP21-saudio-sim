package controller.view;

import model.utility.Vec3f;
import view.utility.TypeSprite;

public interface EnvironmentView {

    /**
     * Set backGround for specific env.
     * @param back path for new Background.
     */
    void setTxBackGround(String back);

    /**
     * Add sprite to set.
     * @param type of new Sprite
     * @param id of sprite
     * @param posElement pos where draw the sprite.
     */
    void addSprite(TypeSprite type, int id, Vec3f posElement);

    /**
     * Get id of the lastSelectedSource if present.
     * @return int Id of source or -1
     */
    int getLastSelectedSource();

    /**
     * Remove the lastSelectedSource.
     */
    void removeSpriteSource();

    /**
     * Change the typeSprite of lastSelectedSource in new Type.
     * @param type to draw
     */
    void upgradeTypeSpriteSource(TypeSprite type);

    /**
     * Set size of space, and change the new pos for all sprites.
     * @param x width
     * @param y height
     */
    void setSize(double x, double y);

    /**
     * Reset set of sprites.
     */
    void reset();
}
