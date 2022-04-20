package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.space.Space;
import model.space.SpaceFactory;
import model.space.SpaceFactoryImpl;
import model.utility.Vec3f;

class SpaceTest {
    private static final Vec3f CENTER = new Vec3f(5f);

    private final SpaceFactory spaceFac = new SpaceFactoryImpl();

    @Test
    void testGetLimit() {
        final Space spc = spaceFac.createDefaultSpace();

        assertEquals(spc.getXmax(), 10f);
        spc.setYMax(0f);
        assertNotEquals(spc.getYmax(), 10f);
    }

    @Test
    void testBorder() {
        final Space spc = spaceFac.createDefaultSpace();

        assertTrue(spc.isAvailable(CENTER, new LinkedList<>()));
        assertFalse(spc.isAvailable(new Vec3f(CENTER.getX() * CENTER.getX()), new LinkedList<>()));
    }

    @Test
    void testBusyPos() {
        final Space spc = spaceFac.createDefaultSpace();
        final List<Vec3f> listPos = new LinkedList<>();

        assertTrue(spc.isAvailable(CENTER, listPos));
        listPos.add(CENTER);
        assertFalse(spc.isAvailable(CENTER, listPos));
    }
}
