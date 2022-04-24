package controller;

/**
 * Interface for controllers. 
 *
 * @param <E> Generic type of the controller.
 */
public interface ControllerApplication<E> {

    /**
     * Set the controller of the view.
     * @param controllerView  the controller view
     */
    void setControllerView(E controllerView);
}
