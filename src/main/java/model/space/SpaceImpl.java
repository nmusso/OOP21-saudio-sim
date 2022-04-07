package model.space;

import java.util.HashSet;
import java.util.Set;

import model.utility.Vec3f;

public class SpaceImpl implements Space {
    private final float scale;
    private final float maxLenght;
    private final float maxWidth;
    //TODO siamo sicuri di tenere le posizioni qui, forse mglio i source hub.

    public SpaceImpl(final float maxLenght, final float maxWidth, final float scale) {
        super();
        this.maxLenght = maxLenght;
        this.maxWidth = maxWidth;
        this.scale = scale;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getLenght() {
        return this.maxLenght;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getWidth() {
        return this.maxWidth;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public float getScale() {
        return this.scale;
    }

    /**
    *
    *{@inheritDoc}
    */
    @Override
    public boolean isAvailable(final Vec3f pos, final Set<Vec3f> positions) {
       if (checkBorder(pos) && checkBusyPos(pos,positions)) {
           return true;
       }
       return false;
    }

    // TODO abilitiare il controllo con lo scale.
    // di conseguenza deve cercare in altre posizioni vicino a quella definita come false
    // con uno sfasamento di this.scale appena ne trova una libera la mette li.

    /**
    *check if the position is out of range.
    *@param vec3f pos to check
    * @return true if pos is ok
    */
    private boolean checkBorder(final Vec3f pos) {
        return ((pos.getX() > 0f || pos.getX() < maxLenght) && (pos.getY() > 0f || pos.getY() < maxWidth));
    }

    /**
    * check busy positions. 
    * @param vec3f pos to check
    * @return true if pos is free else false
    */
    private boolean checkBusyPos(final Vec3f pos, final Set<Vec3f> position) {
        return !position.contains(pos);
    }

}
