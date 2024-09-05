package bricker.lives_ui;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;

/**
 * NumericUI class represents a numerical user interface in the Bricker game.
 * It implements the LivesUIInterface and provides methods for updating and displaying numerical lives.
 * This class uses a TextRenderable to display the numerical representation of lives, updating the text color
 * based on the number of lives remaining.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class NumericUI implements LivesUIInterface {
    private TextRenderable textRenderable;
    private Vector2 topLeftCorner;
    private Vector2 dimensions;
    private final GameObject[] textObject;

    /**
     * Constructs a NumericUI object with the specified TextRenderable for displaying numerical lives.
     */
    public NumericUI() {
        this.textObject = new GameObject[1];
    }

    /**
     * Updates the UI display based on the number of lives remaining, including changing the text color.
     *
     * @param attemptsNum the Counter representing the number of lives remaining.
     */
    @Override
    public void updateLives(Counter attemptsNum) {
        this.textRenderable.setString(Integer.toString(attemptsNum.value()));
        if (attemptsNum.value() == 2) {
            textRenderable.setColor(Color.yellow);
        } else if (attemptsNum.value() == 1) {
            textRenderable.setColor(Color.red);
        } else {
            textRenderable.setColor(Color.green);
        }
    }

    /**
     * Creates a numerical object in the UI with the specified count and sets its initial color to green.
     *
     * @param objectCount the numerical representation of lives to display.
     */
    @Override
    public void createObject(int objectCount) {
        textRenderable = new TextRenderable(Integer.toString(objectCount));
        textRenderable.setColor(Color.green);
        this.textObject[0] = new GameObject(topLeftCorner, dimensions, textRenderable);
    }

    /**
     * Sets the top-left corner position of the UI.
     *
     * @param topLeftCorner the Vector2 representing the top-left corner position.
     */
    @Override
    public void setTopLeftCorner(Vector2 topLeftCorner) {
        this.topLeftCorner = topLeftCorner;
    }

    /**
     * Sets the dimensions of the UI.
     *
     * @param dimensions the Vector2 representing the dimensions of the UI.
     */
    @Override
    public void setDimensions(Vector2 dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Retrieves the numerical GameObject in the UI at the specified location.
     *
     * @param location the index representing the location of the numerical object
     * @return the numerical GameObject at the specified location.
     */
    @Override
    public GameObject getSpecificObject(int location) {
        return textObject[0];
    }
}
