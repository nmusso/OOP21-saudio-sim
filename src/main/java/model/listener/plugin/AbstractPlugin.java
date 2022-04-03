package model.listener;


public abstract class AbstractPlugin implements Plugin {

    private Boolean isEnabled;
    private final Listener listener;

    public AbstractPlugin(final Listener listener) {
        this.listener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enable() {
        this.isEnabled = true;
        restoreSettings();

    }

    public abstract void restoreSettings();

    /**
     * {@inheritDoc}
     */
    @Override
    public void disable() {
        this.isEnabled = false;
        saveSettings();
    }

    public abstract void saveSettings();

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener getListener() {
        return this.listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEnabled() {
        return this.isEnabled;
    }
}
