package model.listener;

import model.audiomanager.Context;

import model.utility.Vec3f;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.EXTEfx;

import static org.lwjgl.openal.AL10.alListener3f;
import static org.lwjgl.openal.AL10.alListenerfv;
import static org.lwjgl.openal.AL10.alListenerf;
import java.util.Objects;

public class ListenerImpl implements Listener {

    private final Context context;
    private Vec3f position;
    private Vec3f atOrientation;
    private Vec3f upOrientation;

    ListenerImpl(final Context context) {
        this(context, new Vec3f(0.0f, 0.0f, 0.0f));
    }

    ListenerImpl(final Context context, final Vec3f position) {
        this(context, position, new Vec3f(0.0f, 0.0f, -1.0f), new Vec3f(0.0f, 1.0f, 0.0f));
    }

    ListenerImpl(final Context context, final Vec3f position, final Vec3f up, final Vec3f at) {
        this.context = context;
        this.setPosition(position);
        this.setOrientation(up, at);
        alListenerf(EXTEfx.AL_METERS_PER_UNIT, 1.0f);
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public final void setPosition(final Vec3f pos) {
        this.position = pos;
        alListener3f(AL10.AL_POSITION, this.position.getX(), this.position.getY(), this.position.getZ());
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public Vec3f getPosition() {
        return this.position;
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public final void setOrientation(final Vec3f up, final Vec3f at) {
        this.atOrientation = at;
        this.upOrientation = up;
        final float[] atUpVec = { this.upOrientation.getX(), this.upOrientation.getY(), this.upOrientation.getZ(),
                this.atOrientation.getX(), this.atOrientation.getY(), this.atOrientation.getZ() };
        alListenerfv(AL10.AL_ORIENTATION, atUpVec);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAtOrientation(final Vec3f at) {
        this.setOrientation(this.upOrientation, at);
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public Vec3f getAtOrientation() {
        return this.atOrientation;
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public Vec3f getUpOrientation() {
        return this.upOrientation;
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public Context getCurrentContext() {
        return this.context;
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return Objects.hash(atOrientation, context, position, upOrientation);
    }

    /**
     * 
     * @inheritDoc
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ListenerImpl other = (ListenerImpl) obj;
        return context.equals(other.getCurrentContext());
    }

}
