package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

/**
 * Paddle class represents the paddle GameObject in the Bricker game.
 * It extends the GameObject class and includes logic for paddle movement and collision handling.
 * This class utilizes a UserInputListener for detecting keyboard input to move the paddle left and right.
 * The paddle's movement speed, window dimensions, and update logic are also defined in this class.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class Paddle extends GameObject {
    /*
     * The movement speed of the paddle.
     */
    private static final float MOVEMENT_SPEED = 300;
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;

    /**
     * Constructs a Paddle object with the specified top-left corner, dimensions, renderable,
     * input listener, and window dimensions.
     *
     * @param topLeftCorner    the Vector2 representing the top-left corner position of the paddle.
     * @param dimensions       the Vector2 representing the dimensions of the paddle.
     * @param renderable       the Renderable object for rendering the paddle.
     * @param inputListener    the UserInputListener for detecting keyboard input.
     * @param windowDimensions the Vector2 representing the dimensions of the game window.
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                  UserInputListener inputListener, Vector2 windowDimensions) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }

    /**
     * Updates the paddle's position based on keyboard input and enforces boundary constraints.
     *
     * @param deltaTime the time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT);
        }

        setVelocity(movementDir.mult(MOVEMENT_SPEED));

        if (getTopLeftCorner().x() + getDimensions().x() > windowDimensions.x()) {
            setTopLeftCorner(new Vector2(windowDimensions.x() - getDimensions().x(), windowDimensions.y()));
        }

        if (getTopLeftCorner().x() < 0) {
            setTopLeftCorner(new Vector2(0, windowDimensions.y()));
        }
    }
}
