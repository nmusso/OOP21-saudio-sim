package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.AudioManager.AudioManager;
import model.environment.Environment;
import model.environment.EnvironmentImpl;

class EnvironmentTest {
    @BeforeAll
    static void init() {
        AudioManager.initContext();
    }
    
    @Test
    public void  SimpleTest() {
        final Environment env = new EnvironmentImpl(null, null);
    }
}
