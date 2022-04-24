package launcher;

import javafx.application.Application;
import view.MainView;

/**
 * Launcher class.
 *
 */
public final class ApplicationLauncher {

    private ApplicationLauncher() {
    }

    /**
     * Main method.
     * @param args the arguments
     */
    public static void main(final String[] args) {
        Application.launch(MainView.class);
    }
}
