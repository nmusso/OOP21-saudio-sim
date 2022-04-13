package model.listener.plugin.controller;


import java.util.Optional;

import controller.MainController;
import controller.view.ListenerControllerView;
import javafx.application.Platform;
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
    private final Thread thVel;

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

        this.thVel = new Thread() {
            @Override
            public void run() {
                //int i = 0;
                while (true) {
                    try {
                        final Vec3f posStart = DropplerPluginController.this.listener.getPosition();
                        Thread.sleep(100);
                        final Vec3f posEnd = DropplerPluginController.this.listener.getPosition();
                        final float x = Math.abs(posStart.getX() - posEnd.getX());
                        final float y = Math.abs(posStart.getY() - posEnd.getY());

                        DropplerPluginController.this.plugin.setVelocity(new Vec3f(x / 0.1f, y / 0.1f, 0.0f));

                        Platform.runLater(() -> DropplerPluginController.this.positionChange());
                        //System.out.println(i++);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        thVel.start();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * 
     */
    private void positionChange() {
        this.controllerView.changeVelocity(this.plugin.getVelocity().getX(), this.plugin.getVelocity().getY());
    }


}
