package view;

import java.io.File;
import java.io.FileInputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MultipleFxmlTest {

    private Pane view;
    private static final String FXML_PATH = "src/main/resources/fxml/";

    /**
    *
    *
    */
    public Pane getPage(final String fileName) {
        final FXMLLoader loader = new FXMLLoader();
        try {
             view = loader.load(new FileInputStream(new File(FXML_PATH + fileName)));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        return view;
    }

}
