package model.source;

public interface Source {
    
    int getId();
    
    void play();
    
    void pause();
    
    void stop();
    
    boolean isPlaying();
    
    int setPosition(Vec3f position);
    
    Vec3f getPositioin();
}
