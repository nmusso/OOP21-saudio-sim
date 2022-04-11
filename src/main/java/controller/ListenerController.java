package controller;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

import controller.view.ListenerControllerView;
import model.listener.Listener;
import model.listener.plugin.controller.ControllerPlugin;
import model.listener.plugin.model.Plugin;

public class ListenerController {
    private ListenerControllerView controllerView;
    private final Listener listener;
    private final Set<Plugin> plugins;

    private final MainController mainCtr;
    public ListenerController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
        this.listener = this.mainCtr.getEnvironmentController().getEnv().getListener();
        this.plugins = new HashSet<>();

    }

    /**
     * 
     */
    public void setControllerView(final ListenerControllerView controllerView) {
        this.controllerView = controllerView;
    }

    /**
     * @throws ClassNotFoundException
     * @throws Exception
     * 
     */
    public void createPluginController(final String name) {
        try {
            final Class<? extends ControllerPlugin> ctrClass = Class.forName("model.listener.plugin.controller." + name + "Controller").asSubclass(ControllerPlugin.class);
            final Constructor<? extends ControllerPlugin> cns = ctrClass.getConstructor(Listener.class, MainController.class, ListenerControllerView.class);
            this.plugins.add(cns.newInstance(this.listener, this.mainCtr, this.controllerView).getPlugin());

        } catch (ClassNotFoundException e) {
            System.err.println("All class for load Plugin: " + name + " not found");
            //this.controllerView.showError("Class not found");
        } catch (Exception e) {
            System.err.println("All class for load Plugin: " + name + " not found");
        }
    }
}
