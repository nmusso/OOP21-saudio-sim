package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import view.utility.PageLoader;

public class ListenerController implements Initializable {
    @FXML private Button btn;
    @FXML private TabPane listenerPane;
    @FXML private MenuItem it;
    @FXML private SplitMenuButton splitMenuPlugin;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        /*TODO con ClassGraph ricercare tutte le classi dei plugin ed aggiungerle in splitMenuPlugin*/
        EventHandler<ActionEvent> eh = (x) -> { 
            System.out.println("PluginX"  + x.getSource());
            };
        MenuItem item = new MenuItem("PluginX");
        item.setOnAction(eh);
        splitMenuPlugin.getItems().add(item);


    }

    @FXML public final void handleAddPlugin(final Event event) {

    }

    @FXML public final void handleSelectPlugin(final Event event) {
        final Tab drp = PageLoader.getPage("src/main/resources/fxml/DropplerPlugin.fxml");
        MenuItem item = (MenuItem) event.getSource();
        item.setDisable(true);
        listenerPane.getTabs().add(drp);
    }

}
