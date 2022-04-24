package view.utility;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import model.utility.Pair;

public final class PageLoader {

    private PageLoader() {

    }
    /**
     * Getter for page and its associated controller.
     * @param <E> the page
     * @param <C> the controller
     * @param fileName the name of the file to be loaded
     * @return an optional of pair page-controller
     */
    public static <E, C> Optional<Pair<E, C>> getPage(final String fileName) {
        E element;
        C ctr;

        final FXMLLoader loader = new FXMLLoader();
        try {
             element = loader.load(PageLoader.class.getResourceAsStream(fileName));
             ctr = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        } 
        return Optional.of(new Pair<>(element, ctr));
    }
}
