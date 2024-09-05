package bricker.lives_ui;

import bricker.gameobjects.Heart;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.utils.Constant.HEART_IMAGE_PATH;
import static bricker.utils.Constant.MAX_LIVES_COUNT;

/**
 * GraphicUI class represents the graphical lives user interface in the Bricker game.
 * It implements the LivesUIInterface and provides methods for updating lives.
 * This class is responsible for managing and rendering the graphical representation of player lives in the
 * game.
 * It utilizes a set of heart GameObjects to represent individual lives, updating the display based on the
 * number of lives remaining.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class GraphicUI implements LivesUIInterface {
    private final ImageReader imageReader;
    private Vector2 topLeftCorner;
    private Vector2 dimensions;
    private final GameObject[] staticHearts;
    private final BrickerGameManager brickerGameManager;
    private final int heartPadding;

    /**
     * Constructs a GraphicUI object with the specified ImageReader, heart padding, and BrickerGameManager.
     *
     * @param imageReader        the image reader for loading heart images
     * @param heartPadding       the padding between hearts in the UI
     * @param brickerGameManager the game manager for managing hearts in the game
     */
    public GraphicUI(ImageReader imageReader,
                     int heartPadding,
                     BrickerGameManager brickerGameManager) {
        this.imageReader = imageReader;
        this.staticHearts = new GameObject[MAX_LIVES_COUNT];
        this.brickerGameManager = brickerGameManager;
        this.heartPadding = heartPadding;
    }

    /**
     * Updates the UI display based on the number of lives remaining.
     *
     * @param attemptsNum the Counter representing the number of lives remaining
     */
    @Override
    public void updateLives(Counter attemptsNum) {
        if (attemptsNum.value() < MAX_LIVES_COUNT) {
            removeHeart(staticHearts[attemptsNum.value()]);
        }
    }

    /**
     * Creates heart GameObjects based on the specified objectCount and positions them in the UI.
     *
     * @param objectCount the number of heart GameObjects to create
     */
    public void createObject(int objectCount) {
        Renderable heartImage = imageReader.readImage(HEART_IMAGE_PATH, true);
        for (int i = 0; i < objectCount; i++) {
            staticHearts[i] = new Heart(new Vector2(topLeftCorner.x() + i * heartPadding,
                    topLeftCorner.y()), dimensions, heartImage);
        }
    }

    /**
     * Remove heart GameObject from the UI.
     *
     * @param heart the heart GameObject to be removed.
     */
    private void removeHeart(GameObject heart) {
        brickerGameManager.removeObject(heart, Layer.UI);
    }

    /**
     * Sets the top-left corner position of the UI.
     *
     * @param topLeftCorner the Vector2 representing the top-left corner position
     */
    @Override
    public void setTopLeftCorner(Vector2 topLeftCorner) {
        this.topLeftCorner = topLeftCorner;
    }

    /**
     * Sets the dimensions of the UI.
     *
     * @param dimensions the Vector2 representing the dimensions of the UI
     */
    @Override
    public void setDimensions(Vector2 dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Retrieves a specific heart GameObject at the specified location.
     *
     * @param location the index representing the location of the heart GameObject
     * @return the heart GameObject at the specified location
     */
    @Override
    public GameObject getSpecificObject(int location) {
        return staticHearts[location];
    }
}
