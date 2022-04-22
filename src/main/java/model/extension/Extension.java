package model.extension;

import model.source.Source;

public interface Extension {
    /**
     * Set the effect in the slot on the source.
     * 
     * @param source the source on which apply the effect
     * @param filter the filter to be applied
     * @param slot   the slot containing the final effect
     * @param num    the number of the effect
     */
    void setOnSource(Source source, int filter, int slot, int num);
}
