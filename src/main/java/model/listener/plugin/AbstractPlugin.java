package model.listener.plugin;

public abstract class AbstractPlugin implements Plugin {

    private Boolean isEnabled;


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
        saveSettings();
        this.isEnabled = false;
    }

    public abstract void saveSettings();


    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEnabled() {
        return this.isEnabled;
    }
}
