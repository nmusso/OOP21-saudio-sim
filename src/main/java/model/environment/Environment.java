package model.environment;

import java.util.List;

import model.listener.SimpleListener;
import model.source.Source;

public interface Environment {

    /**
    *
    * @return A list of all sources
    */
    List<Source> getAllSources();
 
    /**
    * 
    * @return Only source at index X
    */
    Source getXSource(int x);
 
    /**
    * @param The source to add
    * 
    */
    void addSource(Source s);

    /**
    * 
    * @return A list of only playing sources
    */
    List<Source> getPlayingSources();

    /**
    * 
    * @return listener
    */
    SimpleListener getListener();

    /**
    *
    * @return space
    */
    Space getSpace();

    /**
    *
    * Starts playback on all sources.
    */
    void playAllSources();

     /**
    *
    * Stop playback on all sources.
    */
    void stopAllSources();
}
