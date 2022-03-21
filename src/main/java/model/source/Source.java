package model.source;

public interface Source {
    
    public int getId();
    
    public void play();
    
    public void pause();
    
    public void stop();
    
    public boolean isPlaying();
    
    public int setPosition(Vec3f position);
    
    public Vec3f getPositioin();
}
