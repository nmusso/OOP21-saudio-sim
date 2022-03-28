package test;

import org.junit.jupiter.api.Test;

import model.environment.Environment;
import model.environment.EnvironmentImpl;

class EnvironmentTest {
    @Test
    public void  SimpleTest() {
        final Environment env = new EnvironmentImpl(null, null);
    }
}
