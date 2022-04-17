package launcher;

import javafx.application.Application;
import view.MainView;

public final class ApplicationLauncher {

    private ApplicationLauncher() {
    }

    public static void main(final String[] args) {
        Application.launch(MainView.class);
    }
}
