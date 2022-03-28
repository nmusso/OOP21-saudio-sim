package model.environment;

public interface EnvironmentFactory {
    
    Environment CreateMonoEnvironment();
    
    Environment CreateStereoEnvironment();
    
    Environment CreateNEnvironment();
}
