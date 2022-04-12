package controller;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.view.ListenerControllerView;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import model.listener.Listener;
import model.listener.plugin.controller.ControllerPlugin;
import model.listener.plugin.model.Plugin;

public class ListenerController {
    private static final String PLUGIN_PATH = "model.listener.plugin.model";
    private ListenerControllerView controllerView;
    private final Listener listener;
    private final Set<Plugin> plugins;
    private final MainController mainCtr;
    private Set<String> availablePlugin;



    public ListenerController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
        this.listener = this.mainCtr.getEnvironmentController().getEnv().getListener();
        this.plugins = new HashSet<>();
        this.availablePlugin = new HashSet<>();
    }

    /**
     * 
     * @return avaiablePlugin
     */
    public Set<String> getAvailablePlugin() {
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().acceptPackages(PLUGIN_PATH).scan()) {
            final ClassInfoList widgetClasses = scanResult.getClassesImplementing(PLUGIN_PATH + ".Plugin");
            this.availablePlugin = widgetClasses.getNames().stream()
                                                          .map(x -> Stream.of(x.split(PLUGIN_PATH + "."))
                                                                  .collect(Collectors.toList())
                                                                  .get(1))
                                                          .collect(Collectors.toSet());
        } catch (Exception e) {
            System.out.println(e);
            this.controllerView.showError("Unable to load Plugin classes");
        }

        return this.availablePlugin;
    }

    /**
     * @param controllerView
     */
    public void setControllerView(final ListenerControllerView controllerView) {
        this.controllerView = controllerView;
    }

    /**
     * @param name
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
            System.err.println(e);
            this.controllerView.showError("Class not found");
        } catch (Exception e) {
            System.err.println(e);
            this.controllerView.showError("Class not found");
        }
    }

    /**
     * 
     */
    public void positionChanged() {
        this.controllerView.positionChanged();
    }

    /**
     * @return listener
     */
    public Listener getListener() {
        return this.listener;
    }

}
