package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import view.SpaceView;

/**
 * 
 * Controller that communicates with SpaceView and controller.Environmet to communicates with model.space.
 */
public class SpaceController implements ControllerApplication<SpaceView> {

    private static final String PATH = "/json/data.json";

    private final MainController mainCtr;
    private SpaceView ctrlView;

    /**
     * Construct a new SpaceController.
     * @param mainCtr the MainController
     */
    public SpaceController(final MainController mainCtr) {
        this.mainCtr = mainCtr;
    }

    /**
     * Set the controller of the view.
     * @param controllerView  the controller view
     */
    public void setControllerView(final SpaceView controllerView) {
        this.ctrlView = controllerView;
        final List<String> x = new ArrayList<>(); 
        x.add("Empty");
        final String json;
        try (InputStream is = getClass().getResourceAsStream(PATH)) {
            json = new String(is.readAllBytes());
            final JSONObject obj = new JSONObject(json);
            obj.names().toList().stream().forEach(p -> x.add(p.toString()));
            this.ctrlView.addPresetTocmb(x);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
 
    /**
     * Set new size in Env changed by spaceView.
     * @param x width
     * @param y height
     */
    public void setSize(final double x, final double y) {
        this.mainCtr.getEnvironmentController().setSizeEnv(x, y);
    }

    /**
     * Set new Preset for Env changed by spaceView.
     * @param preset new type env.
     */
    public void changePreset(final String preset) {
        this.mainCtr.getEnvironmentController().changeEnv(preset);
    }

    /**
     * Set value for spinner in spaceView.
     * @param x width
     * @param y height
     */
    public void setSpinner(final double x, final double y) {
        this.ctrlView.setX(x);
        this.ctrlView.setY(y);
    }

    /**
     * Enable/disable the objects.
     * @param state the state of the objects
     */
    public void disableChange(final boolean state) {
        ctrlView.disableCombo(state);
    }
}
