package controller;

import java.util.Optional;

import model.audiomanager.AudioManager;
import model.environment.Environment;
import model.environment.EnvironmentFactory;
import model.environment.EnvironmentFactoryImpl;
import model.listener.ListenerFactory;
import model.listener.ListenerFactoryImpl;
import model.source.SourceFactory;
import model.source.SourceFactoryImpl;

public class EnvironmentController {

    private Environment environment;
    private final EnvironmentFactory envFac;
    private final ListenerController listenerCtrl;
    private final SourceController sourceCtrl;

    public EnvironmentController(final ListenerController listenerCtrl, final SourceController sourceCtrl) {
        AudioManager.initContext();
        this.envFac = new EnvironmentFactoryImpl();
        this.listenerCtrl = listenerCtrl;
        this.sourceCtrl = sourceCtrl;
    }

    /**
     * 
     * 
     */
    public void addEnvironment() {
        final SourceFactory sourceFac = new SourceFactoryImpl();
        final ListenerFactory listenerFac = new ListenerFactoryImpl();
        this.environment = envFac.createMonoEnvironment(sourceFac.createFreqRangeSource(), listenerFac.createListener(AudioManager.getContext()), Optional.empty());
    }
}
