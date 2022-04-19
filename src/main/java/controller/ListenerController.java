package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.view.ListenerView;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassGraphException;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import model.listener.Listener;
import model.listener.plugin.ControllerPlugin;
import model.listener.plugin.Plugin;
import model.listener.plugin.PluginManager;

public class ListenerController {
    private static final String PLUGIN_PATH = "plugin.listener.model";
    private static final String CONTROLLER_PATH = "plugin.listener.controller";
    private static final String INTERFACE_PATH = "model.listener.plugin";
    private ListenerView controllerView;
    private final Listener listener;
    private final MainController mainCtr;
    private final PluginManager mng;
    private final Set<ControllerPlugin> pluginsCtr;



    public ListenerController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
        this.listener = this.mainCtr.getEnvironmentController().getEnv().getListener();
        this.mng = new PluginManager();
        this.pluginsCtr = new HashSet<>();
    }

    /**
     * 
     * @return avaiablePlugin
     */
    public Set<String> getAvailablePlugin() {
        Set<String> pluginFound = new HashSet<>();

        try (ScanResult scanResult = new ClassGraph().enableAllInfo().acceptPackages(PLUGIN_PATH).scan()) {
            final ClassInfoList widgetClasses = scanResult.getSubclasses(INTERFACE_PATH + ".AbstractPlugin");
            pluginFound = widgetClasses.getNames().stream()
                                                      .map(x -> Stream.of(x.split(PLUGIN_PATH + "."))
                                                              .collect(Collectors.toList())
                                                              .get(1))
                                                      .collect(Collectors.toSet());

            pluginFound.removeAll(this.mng.getPlugins().stream().map(Plugin::getClassName).collect(Collectors.toSet()));
        } catch (ClassGraphException e) {
            e.printStackTrace();
            this.controllerView.showError("Error to access in ClassPath");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            this.controllerView.showError("Error in class name");
        }

        return pluginFound;
    }

    /**
     * @param controllerView
     */
    public void setControllerView(final ListenerView controllerView) {
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
            final Class<? extends ControllerPlugin> ctrClass = Class.forName(CONTROLLER_PATH + "." + name + "Controller").asSubclass(ControllerPlugin.class);
            final Constructor<? extends ControllerPlugin> cns = ctrClass.getConstructor(Listener.class, MainController.class, ListenerView.class);
            final ControllerPlugin ctr = cns.newInstance(this.listener, this.mainCtr, this.controllerView);
            this.mng.addPlugin(ctr.getPlugin());
            this.pluginsCtr.add(ctr);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this.controllerView.showError("Class: " + name + " not found");
        } catch (InstantiationException e) {
            e.printStackTrace();
            this.controllerView.showError("Error on instantiation class: " + name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            this.controllerView.showError("Illegal access class" + name);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            this.controllerView.showError("Illegal argument in  class " + name);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            this.controllerView.showError("Class not found");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            this.controllerView.showError("Constructor not found in class" + name);
        } catch (SecurityException e) {
            e.printStackTrace();
            this.controllerView.showError("Class not found2");
        } 
    }

    /**
     * @return plugin manager istance.
     */
    public PluginManager getPluginManager() {
        return this.mng;
    }

    /**
     * 
     */
    public void stopAllPlugin() {
        this.pluginsCtr.forEach(ctr -> ctr.removePlugin());
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

    /**
     * 
     */
    public void enabledAll() {
        this.mng.enableAll();
    }

    /**
     * 
     */
    public void disableAll() {
        this.mng.disableAll();
    }


}
