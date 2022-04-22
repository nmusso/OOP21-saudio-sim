package plugin.listener.controller;

import java.util.Optional;

import controller.MainController;
import model.listener.Listener;
import plugin.listener.model.Plugin;
import plugin.listener.model.SoundLevelMeterPlugin;
import plugin.listener.view.SoundLevelMeterPluginView;
import plugin.listener.view.utility.PluginViewLoader;
import view.ListenerView;

/**
 * 
 * Controller for SoundLevelMeterPlugin allows communications between model and view.
 *
 */
public class SoundLevelMeterPluginController implements ControllerPlugin {
    private static final String FXML_VIEW_PATH = "/fxml/soundLevelMeterPlugin.fxml";
    private final SoundLevelMeterPlugin plugin;
    private final SoundLevelMeterPluginView controllerView;
    private final MainController mainController;
    private final ThreadMeter thMeter;

    public SoundLevelMeterPluginController(final Listener listener, final MainController mainController, final ListenerView listenerView) throws ClassNotFoundException {
        final Optional<SoundLevelMeterPluginView> temp = PluginViewLoader.tabPluginLoader(FXML_VIEW_PATH);

        if (temp.isEmpty()) {
            throw new ClassNotFoundException();
        }
        this.controllerView = temp.get();
        this.controllerView.setControllerApplication(this);
        this.controllerView.setListenerControllerView(listenerView);

        this.mainController = mainController;
        this.plugin = new SoundLevelMeterPlugin(listener);

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlugin() {
        this.thMeter.stopTh();
        this.mainController.getListenerCtr().getPluginManager().removePlugin(this.plugin);

    }

    /**
     * 
     * Thread for real-time indicator update in the view. 
     *
     */
    class ThreadMeter extends Thread {
        private boolean isRunning = true;

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            while (this.isRunning) {
                try {
                    SoundLevelMeterPluginController.this.plugin.setSourceHub(SoundLevelMeterPluginController.this.mainController.getEnvironmentController().getEnv().getSourceHub());
                    final var color = SoundLevelMeterPluginController.this.plugin.getRgbColor();
                    SoundLevelMeterPluginController.this.controllerView.setColor(color);
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        /**
         * Stop and terminate the thread.
         */
       public void stopTh() {
           this.isRunning = false;
       }

    }

}
