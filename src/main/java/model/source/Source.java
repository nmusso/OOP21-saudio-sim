package model.source;

import model.utility.Vec3f;

public interface Source {


    /**
     * Returns the id of the Source.
     * 
     * @return id
     */
    int getId();

    /**
     * Starts playback.
     * 
     */
    void play();

    /**
     * Pauses playback.
     * 
     */
    void pause();

    /**
     * Stop playback.
     * 
     */
    void stop();

    /**
     * Sets the position of the Source.
     * 
     * @param position
     */
    void setPosition(Vec3f position);

    /**
     * Gets the position of the Source.
     * 
     * @return position
     */
    Vec3f getPositioin();
}
