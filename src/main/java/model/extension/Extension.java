package model.extension;

public interface Extension {
    void set(Extension type, int attr, int val);
    void apply(int val, int source);
}
