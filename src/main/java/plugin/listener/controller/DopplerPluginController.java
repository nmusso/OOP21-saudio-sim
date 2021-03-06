package plugin.listener.controller;

import java.util.Optional;

import controller.MainController;
import javafx.application.Platform;
import model.listener.Listener;
import model.utility.Vec3f;
import plugin.listener.model.DopplerPlugin;
import plugin.listener.model.Plugin;
import plugin.listener.view.DopplerPluginView;
import plugin.listener.view.utility.PluginViewLoader;
import view.ListenerView;

/**
 * 
 * Controller for the DopplerPlugin allows communications between model and view.
 *
 */
public class DopplerPluginController implements ControllerPlugin {
    private static final long TIME_LAPSE_VELOCITY = 100;
    private static final String FXML_VIEW_PATH = "/fxml/dopplerPlugin.fxml";
    private final DopplerPluginView controllerView;
    private final Listener listener;
    private final MainController mainController;
    private final DopplerPlugin plugin;
    private final ThreadVelocity thVel;

    /**
     * Construct a new DopplerPluginController.
     * @param listener the listener
     * @param mainController the MainController
     * @param listenerView the ListenerView
     * @throws ClassNotFoundException if the file of the plugin doesn't exists.
     */
    public DopplerPluginController(final Listener listener, final MainController mainController, final ListenerView listenerView) throws ClassNotFoundException {
        final Optional<DopplerPluginView> temp = PluginViewLoader.tabPluginLoader(FXML_VIEW_PATH);

        if (temp.isEmpty()) {
            throw new ClassNotFoundException();
        }
        this.controllerView = temp.get();
        this.controllerView.setControllerApplication(this);
        this.controllerView.setListenerControllerView(listenerView);

        this.listener = listener;
        this.mainController = mainController;
        this.plugin = new DopplerPlugin();

        this.thVel = new ThreadVelocity();
        this.thVel.start();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * Update velocity when the listener moves.
     */
    private void positionChange() {
        this.controllerView.changeVelocity(this.plugin.getVelocity().getX(), this.plugin.getVelocity().getY());
    }

    /**
     * {@inheritDoc}
     */
    public void removePlugin() {
        this.thVel.stopTh();
        this.mainController.getListenerCtr().getPluginManager().removePlugin(this.plugin);
    }


    /**
     * 
     * Thread for calculate real-time velocity of listener.
     *
     */
    private class ThreadVelocity extends Thread {
        private boolean isRunning = true;

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            while (this.isRunning) {
                try {
                    final Vec3f posStart = DopplerPluginController.this.listener.getPosition();
                    Thread.sleep(TIME_LAPSE_VELOCITY);
                    final Vec3f posEnd = DopplerPluginController.this.listener.getPosition();
                    final float x = Math.abs(posStart.getX() - posEnd.getX());
                    final float y = Math.abs(posStart.getY() - posEnd.getY());

                    DopplerPluginController.this.plugin.setVelocity(
                            new Vec3f(x / (TIME_LAPSE_VELOCITY / 1000f), y / (TIME_LAPSE_VELOCITY / 1000f), 0.0f));
                    Platform.runLater(() -> DopplerPluginController.this.positionChange());

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
