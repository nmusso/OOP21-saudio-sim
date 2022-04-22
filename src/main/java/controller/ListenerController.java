package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassGraphException;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import model.listener.Listener;
import plugin.listener.controller.ControllerPlugin;
import plugin.listener.model.Plugin;
import plugin.listener.model.PluginManager;
import view.ListenerView;

/**
 * 
 * Controller class for the listener, allow to communicate Listener and ListenerView. 
 * This controller can create plug-in controllers.
 */
public class ListenerController implements ControllerApplication<ListenerView> {
    private static final String PLUGIN_PATH = "plugin.listener.model";
    private static final String CONTROLLER_PATH = "plugin.listener.controller";
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
     * Access to the class path to get the plug-ins present.
     * @return avaiablePlugin
     */
    public Set<String> getAvailablePlugin() {
        Set<String> pluginFound = new HashSet<>();

        try (ScanResult scanResult = new ClassGraph().enableAllInfo().acceptPackages(PLUGIN_PATH).scan()) {
            final ClassInfoList widgetClasses = scanResult.getSubclasses(PLUGIN_PATH + ".AbstractPlugin");
            pluginFound = widgetClasses.getNames().stream()
                                                      .map(x -> Stream.of(x.split(PLUGIN_PATH + "."))
                                                              .collect(Collectors.toList())
                                                              .get(1))
                                                      .collect(Collectors.toSet());

            pluginFound.removeAll(this.mng.getPlugins().stream().map(Plugin::getClassName).collect(Collectors.toSet()));
        } catch (ClassGraphException e) {
            e.printStackTrace();
            this.controllerView.showMessage("Error to access in ClassPath");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            this.controllerView.showMessage("Error in class name");
        }

        return pluginFound;
    }

    /**
     * {@inheritDoc}
     * @param controllerView
     */
    @Override
    public void setControllerView(final ListenerView controllerView) {
        this.controllerView = controllerView;
    }

    /**
     * Instance via reflection the plug-in controller passed as a parameter.
     * @param name
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
            this.controllerView.showMessage("Class: " + name + " not found");
        } catch (InstantiationException e) {
            e.printStackTrace();
            this.controllerView.showMessage("Error on instantiation class: " + name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            this.controllerView.showMessage("Illegal access class" + name);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            this.controllerView.showMessage("Illegal argument in  class " + name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            this.controllerView.showMessage("Constructor not found in class" + name);
        } catch (SecurityException | InvocationTargetException e) {
            e.printStackTrace();
            this.controllerView.showMessage("Error while loading plugin: " + name + " classes");
        } 
    }

    /**
     * @return plug-in manager instance.
     */
    public PluginManager getPluginManager() {
        return this.mng;
    }

    /**
     * Remove all plug-in in execution.
     */
    public void stopAllPlugin() {
        this.pluginsCtr.forEach(ctr -> ctr.removePlugin());
    }

    /**
     * Update the view when there is a shift of the listener.
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
     * Enable all plug-in loaded.
     */
    public void enableAll() {
        this.mng.enableAll();
    }

    /**
     * Disable all plug-in loaded.
     */
    public void disableAll() {
        this.mng.disableAll();
    }


}
