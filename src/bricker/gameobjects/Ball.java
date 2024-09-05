package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Ball class represents a ball GameObject in the Bricker game.
 * It extends the GameObject class and adds functionality specific to the behavior of a game ball.
 * This class includes a collision counter and a collision sound. The collision counter is incremented each
 * time the ball collides with another GameObject, and the collision sound is played on each collision.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class Ball extends GameObject {
    private int collisionCounter = 0;
    private final Sound collisionSound;

    /**
     * Constructs a Ball object with the specified top-left corner, dimensions, renderable, and collision
     * sound.
     *
     * @param topLeftCorner  the Vector2 representing the top-left corner position of the ball.
     * @param dimensions     the Vector2 representing the dimensions of the ball.
     * @param renderable     the Renderable object for rendering the ball.
     * @param collisionSound the Sound object for playing collision sounds.
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
    }

    /**
     * Gets the current collision counter value.
     *
     * @return the current collision counter value.
     */
    public int getCollisionCounter() {
        return collisionCounter;
    }

    /**
     * Handles the behavior when the ball collides with another GameObject.
     * Increments the collision counter, reflects the velocity based on the collision normal,
     * and plays the collision sound.
     *
     * @param other     the other GameObject involved in the collision.
     * @param collision the Collision object representing the collision details.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        collisionCounter++;
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        collisionSound.play();
    }
}
