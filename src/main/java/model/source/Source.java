package model.source;

import model.utility.Vec3f;

public interface Source {
    
    int getId();
    
    void play();
    
    void pause();
    
    void stop();
    
    boolean isPlaying();
    
    void setPosition(Vec3f position);
    
    Vec3f getPositioin();
}