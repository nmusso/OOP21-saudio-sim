package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
        
        private boolean ok = true;
        
        @Test
        public void testBasic1() {
                assertEquals(42, Integer.sum(19, 23));
        }
        
        @Test
        public void testBasic2() {
                assertEquals(42, Integer.sum(19, 23));
        }
        
        @Test
        public void testBasic3() {
                assertEquals(42, Integer.sum(19, 23));
        }

}