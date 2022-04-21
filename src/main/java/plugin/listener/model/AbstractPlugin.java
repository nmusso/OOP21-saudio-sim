package plugin.listener.model;

import java.util.Objects;

/**
 * 
 * The abstract plug-in class with Template method for saving
 * and restore plug-in parameters.
 *
 */
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

    /**
     * Restore the previous parameter saved.
     */
    protected abstract void restoreSettings();

    /**
     * {@inheritDoc}
     */
    @Override
    public void disable() {
        saveSettings();
        this.isEnabled = false;
    }

    /**
     * Save the current values of the parameters.
     */
    protected abstract void saveSettings();


    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEnabled() {
        return this.isEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractPlugin other = (AbstractPlugin) obj;
        return this.getClassName().equals(other.getClassName());
    }
}
