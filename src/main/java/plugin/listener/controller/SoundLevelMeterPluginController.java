package plugin.listener.controller;

import java.util.Optional;

import controller.MainController;
import controller.view.ListenerView;
import model.listener.Listener;
import model.listener.plugin.ControllerPlugin;
import model.listener.plugin.Plugin;
import model.listener.plugin.view.utility.PluginViewLoader;
import plugin.listener.model.SoundLevelMeterPlugin;

public class SoundLevelMeterPluginController implements ControllerPlugin {
    private static final String FXML_VIEW_PATH = "/fxml/soundLevelMeterView.fxml";
    private final SoundLevelMeterPlugin plugin;
    private final MainController mainController;
    private final Listener listener;
    private final ThreadMeter thMeter;

    public SoundLevelMeterPluginController(final Listener listener, final MainController mainController, final ListenerView listenerView) throws ClassNotFoundException {
        /*final Optional<SoundLevelMeterPluginView> temp = PluginViewLoader.tabPluginLoader(FXML_VIEW_PATH);

        if (temp.isEmpty()) {
            throw new ClassNotFoundException();
        }*/

        this.mainController = mainController;
        this.listener = listener;
        this.plugin = new SoundLevelMeterPlugin(mainController.getEnvironmentController().getEnv().getSourceHub(), listener);

        this.thMeter = new ThreadMeter();
        this.thMeter.start();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }


    class ThreadMeter extends Thread {
        private boolean isRunning = true;

        @Override
        public void run() {
            while (this.isRunning) {
                try {
                    SoundLevelMeterPluginController.this.plugin.sourceDistanceMin();
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

       public void stopTh() {
           this.isRunning = false;
       }

    }

}
