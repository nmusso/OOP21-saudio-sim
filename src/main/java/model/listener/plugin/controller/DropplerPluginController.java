package model.listener.plugin.controller;

import java.util.Optional;

import controller.MainController;
import controller.view.ListenerControllerView;
import model.listener.Listener;
import model.listener.plugin.model.Plugin;
import model.listener.plugin.view.DropplerPluginControllerView;
import model.listener.plugin.view.utility.PluginViewLoader;

public class DropplerPluginController implements ControllerPlugin {
    private static final String FXML_VIEW_PATH = "src/main/resources/fxml/DropplerPlugin.fxml";
    private final DropplerPluginControllerView controllerView;
    private final Listener listener;
    private final MainController mainController;

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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Plugin getPlugin() {
        // TODO Auto-generated method stub
        return null;
    }
}
