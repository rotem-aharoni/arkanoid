package bricker.gameobjects;

import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Brick class represents a brick GameObject in the Bricker game.
 * It extends the GameObject class and includes a CollisionStrategy for defining the behavior
 * when the brick collides with another GameObject.
 * This class also maintains a flag indicating whether the brick is destroyed to prevent multiple collisions
 * from triggering the collision strategy multiple times.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class Brick extends GameObject {
    private final CollisionStrategy collisionStrategy;
    private Boolean isDestroyed = false;

    /**
     * Constructs a Brick object with the specified top-left corner, dimensions, renderable, and collision
     * strategy.
     *
     * @param topLeftCorner     the Vector2 representing the top-left corner position of the brick.
     * @param dimensions        the Vector2 representing the dimensions of the brick.
     * @param renderable        the Renderable object for rendering the brick.
     * @param collisionStrategy the CollisionStrategy defining the behavior on collision with another
     *                          GameObject.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, CollisionStrategy
            collisionStrategy) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy = collisionStrategy;
    }

    /**
     * Handles the behavior when the brick collides with another GameObject.
     * Invokes the collision strategy only if the brick is not already destroyed.
     * Marks the brick as destroyed to prevent multiple collisions triggering the strategy.
     *
     * @param other     the other GameObject involved in the collision.
     * @param collision the Collision object representing the collision details.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (!isDestroyed) {
            collisionStrategy.onCollision(this, other);
            isDestroyed = true;
        }
    }
}
