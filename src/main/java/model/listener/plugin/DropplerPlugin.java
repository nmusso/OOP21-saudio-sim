package model.listener.plugin;


import model.utility.Vec3f;

public class DropplerPlugin extends AbstractPlugin {
    private Vec3f velocity;
    private float dropplerLv;

    public DropplerPlugin() {
        this.velocity = new Vec3f(0.0f, 0.0f, 0.0f);
        this.dropplerLv = 1.0f;
    }

    @Override
    public void restoreSettings() {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveSettings() {
        // TODO Auto-generated method stub

    }

}
