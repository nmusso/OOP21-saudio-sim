package plugin.listener.view.utility;

import java.util.Optional;

import javafx.scene.control.Tab;
import model.utility.Pair;
import view.utility.PageLoader;

/**
 * Utility class for Load plug-in view (fxml).
 *
 */
public final class PluginViewLoader {
    private PluginViewLoader() { }

    /**
     * Load view and get the controller-view associated if present.
     * @param <C> controller type.
     * @param fileName the name of the file.
     * @return optional of controller-view.
     */
    public static <C> Optional<C> tabPluginLoader(final String fileName) {
        final Optional<Pair<Tab, C>> tabInfo = PageLoader.getPage(fileName);
        return tabInfo.isPresent() ? Optional.of(tabInfo.get().getY()) : Optional.empty();

    }
}
