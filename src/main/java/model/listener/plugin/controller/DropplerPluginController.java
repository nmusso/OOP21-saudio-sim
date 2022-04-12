package model.listener.plugin.controller;

import java.util.Optional;

import controller.MainController;
import controller.view.ListenerControllerView;
import model.listener.Listener;
import model.listener.plugin.model.DropplerPlugin;
import model.listener.plugin.model.Plugin;
import model.listener.plugin.view.DropplerPluginControllerView;
import model.listener.plugin.view.utility.PluginViewLoader;
import model.utility.Vec3f;

public class DropplerPluginController implements ControllerPlugin {
    private static final String FXML_VIEW_PATH = "src/main/resources/fxml/DropplerPlugin.fxml";
    private final DropplerPluginControllerView controllerView;
    private final Listener listener;
    private final MainController mainController;
    private final DropplerPlugin plugin;
    private long startTime;
    private Vec3f lastPosition;

    public DropplerPluginController(final Listener listener, final MainController mainController, final ListenerControllerView listenerView) throws ClassNotFoundException {
        final Optional<DropplerPluginControllerView> temp = PluginViewLoader.tabPluginLoader(FXML_VIEW_PATH);

        if (temp.isEmpty()) {
            throw new ClassNotFoundException();
        }
        this.controllerView = temp.get();
        this.controllerView.setControllerApplication(this);
        this.controllerView.setListenerControllerView(listenerView);

        this.listener = listener;
        this.mainController = mainController;
        this.plugin = new DropplerPlugin();
        this.lastPosition = listener.getPosition();
        this.startTime = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * TODO check divisione per 0 e l ordine delle istruzioni .
     */
    public void positionChange() {
        final long endTime = System.currentTimeMillis();
        final long elapsedTime = endTime - this.startTime;
        final Vec3f spaceTraveled = new Vec3f(Math.abs(this.listener.getPosition().getX() - this.lastPosition.getX()),
                                              Math.abs(this.listener.getPosition().getY() - this.lastPosition.getY()),
                                              Math.abs(this.listener.getPosition().getZ() - this.lastPosition.getZ()));
        this.plugin.setVelocityX(spaceTraveled.getX() / elapsedTime);
        this.plugin.setVelocityY(spaceTraveled.getY() / elapsedTime);
        this.startTime = endTime; 
        this.lastPosition = this.listener.getPosition();
        this.controllerView.changeVelocity(this.plugin.getVelocity().getX(), this.plugin.getVelocity().getY());
    }

}
