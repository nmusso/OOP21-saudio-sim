package view.utility;

import java.io.File;
import java.io.FileInputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class PageLoader {

    private static Pane view;

    /**
    *
    */
    public static Pane getPage(final String fileName) {
        final FXMLLoader loader = new FXMLLoader();
        try {
             view = loader.load(new FileInputStream(new File(fileName)));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        return view;
    }

}
