package controller;


import java.net.URL;
<<<<<<< HEAD:src/main/java/controller/ListenerController.java
import java.util.Optional;
=======
>>>>>>> d01066a2f8f163964cf399d911c5bb67f9923333:src/main/java/controller/ListenerControllerView.java
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
import model.utility.Pair;
import view.utility.PageLoader;

<<<<<<< HEAD:src/main/java/controller/ListenerController.java
public class ListenerController implements Initializable, ControllerView {
=======
public class ListenerControllerView implements Initializable, ControllerView {
>>>>>>> d01066a2f8f163964cf399d911c5bb67f9923333:src/main/java/controller/ListenerControllerView.java
    @FXML private Button btn;
    @FXML private TabPane listenerPane;
    @FXML private MenuItem it;
    @FXML private SplitMenuButton splitMenuPlugin;
    private static final String FXML_PATH = "src/main/resources/fxml/";
    private ListenerControllerApplication ctrListener;


    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        /*TODO con ClassGraph ricercare tutte le classi dei plugin ed aggiungerle in splitMenuPlugin*/

        final EventHandler<ActionEvent> eh = (x) -> { 
            final MenuItem thisItem = (MenuItem) (x.getSource());
            splitMenuPlugin.setText(thisItem.getText());
            thisItem.setDisable(true);
            };

        final MenuItem item = new MenuItem("DropplerPlugin");
        item.setOnAction(eh);
        splitMenuPlugin.getItems().add(item);



    }

    /*TODO review getText is ""*/
    @FXML public final void handleAddPlugin(final Event event) throws ClassNotFoundException {
        final Optional<Pair<Tab, ControllerView>> plugin = PageLoader.getPage(FXML_PATH + splitMenuPlugin.getText() + ".fxml");
        listenerPane.getTabs().add(plugin.get().getX());

        splitMenuPlugin.setText("");
    }

    @FXML public final void handleSelectPlugin(final Event event) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainControllerApplication ctrMain) {
        this.ctrListener = ctrMain.getListenerCtr();
    }

}
