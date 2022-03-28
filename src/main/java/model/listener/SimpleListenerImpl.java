package model.listener;

import model.utility.Vec3f;
import static org.lwjgl.openal.AL10.*;

public class SimpleListenerImpl implements SimpleListener{

    private Vec3f position;
    private Vec3f atOrientation;
    private Vec3f upOrientation;

    @Override
    public void setPosition( Vec3f pos) {
        alListener3f(AL_POSITION, this.position.getX(), this.position.getY(), this.position.getZ());
        
    }

    @Override
    public Vec3f getPosition() {        
        return this.position;
    }

    @Override
    public void setOrientation(Vec3f at, Vec3f up) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vec3f getAtOrientation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vec3f getUpOrientation() {
        // TODO Auto-generated method stub
        return null;
    }
    

}
