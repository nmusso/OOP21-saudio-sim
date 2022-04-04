package view;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MultipleFxmlTest {

    private Pane view;
    
    public Pane getPage (final String fileName ) {
        
        try {
             URL fileUrl = MainView.class.getResource(fileName);
             view = new FXMLLoader().load(fileUrl);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        return view;
    }
    
}
