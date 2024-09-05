package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.util.Counter;

/**
 * BasicCollisionStrategy is a class that implements the CollisionStrategy interface, defining a simple
 * collision behavior for the game involving bricks. It decrements a brick count and removes the collided
 * brick from the game manager upon collision.
 * This class is designed to be extended or used as a base class for specific collision behaviors in a
 * brick-based game.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class BasicCollisionStrategy implements CollisionStrategy {
    /**
     * The BrickerGameManager instance associated with this object.
     * This field represents the game manager responsible for controlling the game's logic, including
     * initialization, updates, and interactions.
     */
    protected final BrickerGameManager brickerGameManager;
    private final Counter brickCount;


    /**
     * Constructs a BasicCollisionStrategy with the specified BrickerGameManager and Counter for brick count.
     *
     * @param brickerGameManager the game manager managing bricks in the game.
     * @param brickCount         the counter representing the number of bricks in the game.
     */
    public BasicCollisionStrategy(BrickerGameManager brickerGameManager, Counter brickCount) {
        this.brickerGameManager = brickerGameManager;
        this.brickCount = brickCount;
    }

    /**
     * Handles the collision between two GameObjects by decrementing the brick count and removing the collided
     * brick from the BrickerGameManager.
     *
     * @param object1 the first GameObject involved in the collision.
     * @param object2 the second GameObject involved in the collision.
     */
    @Override
    public void onCollision(GameObject object1, GameObject object2) {
        brickCount.decrement();
        brickerGameManager.removeObject(object1, Layer.STATIC_OBJECTS);

    }
}
