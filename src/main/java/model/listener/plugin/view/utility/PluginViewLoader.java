package model.listener.plugin.view.utility;

import java.util.Optional;

import javafx.scene.control.Tab;
import model.utility.Pair;
import view.utility.PageLoader;


public final class PluginViewLoader {
    private PluginViewLoader() { }

    public static <C> Optional<C> tabPluginLoader(final String fileName) {
        final PageLoader pl = new PageLoader();
        final Optional<Pair<Tab, C>> tabInfo = pl.getPage(fileName);
        return tabInfo.isPresent() ? Optional.of(tabInfo.get().getY()) : Optional.empty();

    }
}
