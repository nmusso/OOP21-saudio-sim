package model.environment;

import java.util.List;

import model.listener.SimpleListener;
import model.source.Source;

public interface Environment {
    
    int getIdEnvironment();
    
    List<Source> getAllSources();
    
    Source getXSource(int x);
    
    List<Source> getPlayingSources();
    
    SimpleListener getListener();
}
