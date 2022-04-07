package view.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import model.utility.Pair;

public final class PageLoader {

    private PageLoader() { }
    /**
    *
    */
    public static <E, C> Optional<Pair<E, C>> getPage(final String fileName) {
        E element;
        C ctr;

        final FXMLLoader loader = new FXMLLoader();
        try {
             element = loader.load(new FileInputStream(new File(fileName)));
             ctr = loader.getController();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return Optional.empty();
        } 
        return Optional.of(new Pair<>(element, ctr));
    }
}
