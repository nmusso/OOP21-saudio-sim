package plugin.listener.controller;

import java.util.Optional;

import controller.MainController;
import controller.view.ListenerView;
import model.listener.Listener;
import model.listener.plugin.ControllerPlugin;
import model.listener.plugin.Plugin;
import model.listener.plugin.view.utility.PluginViewLoader;
import plugin.listener.model.SoundLevelMeterPlugin;
import plugin.listener.view.SoundLevelMeterPluginView;

public class SoundLevelMeterPluginController implements ControllerPlugin {
    private static final String FXML_VIEW_PATH = "/fxml/soundLevelMeterPlugin.fxml";
    private final SoundLevelMeterPlugin plugin;
    private final SoundLevelMeterPluginView controllerView;
    private final MainController mainController;
    private final Listener listener;
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
        this.listener = listener;
        this.plugin = new SoundLevelMeterPlugin(mainController.getEnvironmentController().getEnv().getSourceHub(), this.listener);

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
                    final var color = SoundLevelMeterPluginController.this.plugin.getRgbColor();
                    SoundLevelMeterPluginController.this.controllerView.setColor(color);
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

       public void stopTh() {
           this.isRunning = false;
       }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlugin() {
        this.thMeter.stopTh();
        this.mainController.getListenerCtr().removePlugin(this.plugin.getClassName());

    }

}
