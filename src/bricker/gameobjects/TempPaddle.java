package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.utils.Constant.BALL_TAG_NAME;
import static bricker.utils.Constant.PUCK_TAG_NAME;

/**
 * TempPaddle class represents a temporary paddle GameObject in the Bricker game.
 * It extends the Paddle class and includes additional functionality for tracking collisions.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class TempPaddle extends Paddle {

    private final Counter paddleCollisionCounter;

    /**
     * Constructs a TempPaddle object with the specified parameters.
     *
     * @param topLeftCorner          The top-left corner position of the paddle.
     * @param dimensions             The dimensions (width and height) of the paddle.
     * @param renderable             The Renderable object for rendering the paddle.
     * @param inputListener          The UserInputListener for handling user input.
     * @param windowDimensions       The dimensions of the game window.
     * @param paddleCollisionCounter The counter for tracking paddle collisions.
     */
    public TempPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, UserInputListener
            inputListener, Vector2 windowDimensions, Counter paddleCollisionCounter) {
        super(topLeftCorner, dimensions, renderable, inputListener, windowDimensions);
        this.paddleCollisionCounter = paddleCollisionCounter;
    }

    /**
     * Overrides the shouldCollideWith method from the superclass (Paddle).
     * Specifies that the TempPaddle should only collide with BALL_TAG_NAME or PUCK_TAG_NAME objects.
     *
     * @param other The GameObject to check for collision.
     * @return True if the TempPaddle should collide with the given GameObject; otherwise, false.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return super.shouldCollideWith(other) && (other.getTag().equals(BALL_TAG_NAME) ||
                other.getTag().equals(PUCK_TAG_NAME));
    }

    /**
     * Overrides the onCollisionEnter method from the superclass (Paddle).
     * Decrements the paddleCollisionCounter when a collision occurs.
     *
     * @param other     The GameObject involved in the collision.
     * @param collision The Collision object representing the collision details.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        paddleCollisionCounter.decrement();
    }

    /**
     * Checks if the paddle's work has ended by verifying if the paddleCollisionCounter is zero.
     *
     * @return True if the paddle's work has ended; otherwise, false.
     */
    public boolean checkIfPaddleWorkEnd() {
        return paddleCollisionCounter.value() == 0;
    }
}
