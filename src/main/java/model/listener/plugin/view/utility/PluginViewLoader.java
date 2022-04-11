package model.listener.plugin.view.utility;

import java.util.Optional;

import javafx.scene.control.Tab;
import model.utility.Pair;


public final class PluginViewLoader {
    private PluginViewLoader() { }

    public static <C> Optional<C> tabPluginLoader(final String fileName) {
        final Optional<Pair<Tab, C>> tabInfo = Optional.empty(); //PageLoader.getPage(fileName);
        return tabInfo.isPresent() ? Optional.of(tabInfo.get().getY()) : Optional.empty();

    }
}
