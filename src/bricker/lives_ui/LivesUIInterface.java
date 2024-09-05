package bricker.lives_ui;

import danogl.GameObject;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 * LivesUIInterface is an interface defining methods for managing the user interface related to lives in the
 * Bricker game.
 * Classes implementing this interface are expected to handle the visual representation of lives, such as
 * updating the display, creating objects, setting positions, and retrieving specific objects.
 * Implementing classes are responsible for defining the behavior of these methods to suit the needs of the
 * game's UI.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public interface LivesUIInterface {

    /**
     * Updates the UI display based on the number of lives remaining.
     *
     * @param attemptsNum the Counter representing the number of lives remaining
     */
    void updateLives(Counter attemptsNum);

    /**
     * Creates objects in the UI based on the specified count.
     *
     * @param objectCount the number of objects to create
     */
    void createObject(int objectCount);

    /**
     * Sets the top-left corner position of the UI.
     *
     * @param topLeftCorner the Vector2 representing the top-left corner position
     */
    void setTopLeftCorner(Vector2 topLeftCorner);

    /**
     * Sets the dimensions of the UI.
     *
     * @param dimensions the Vector2 representing the dimensions of the UI
     */
    void setDimensions(Vector2 dimensions);

    /**
     * Retrieves a specific object at the specified location in the UI.
     *
     * @param location the index representing the location of the object
     * @return the object at the specified location
     */
    GameObject getSpecificObject(int location);
}
