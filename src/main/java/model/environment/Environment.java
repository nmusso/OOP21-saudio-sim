package model.environment;

import java.util.List;

import model.listener.SimpleListener;
import model.source.Source;

public interface Environment {
    /**
    *
    * 
    *
    */
    List<Source> getAllSources();
    /**
    *
    * 
    *
    */
    Source getXSource(int x);
    /**
    *
    * 
    *
    */
    void addSource(Source s);
    /**
    *
    * 
    *
    */
    List<Source> getPlayingSources();
    /**
    *
    * 
    *
    */
    SimpleListener getListener();
    /**
    *
    * 
    *
    */
    Space getSpace();
    /**
    *
    * 
    *
    */
    void playAllSources();
    /**
    *
    * 
    *
    */
    void stopAllSources();
}
