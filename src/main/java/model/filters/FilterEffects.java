package model.filters;

public interface FilterEffects {
    void set(FilterEffects type);
    void apply(int source);
}