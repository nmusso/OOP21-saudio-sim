package view.utility;

import java.io.File;
import java.io.FileInputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class PageLoader {

    /**
    *
    */
    public static <E> E getPage(final String fileName) {
        E element = null;
        final FXMLLoader loader = new FXMLLoader();
        try {
             element = loader.load(new FileInputStream(new File(fileName)));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        return element;
    }
}
