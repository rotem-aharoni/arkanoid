package bricker.brick_strategies;

import danogl.GameObject;


/**
 * CollisionStrategy is an interface representing a strategy for handling collisions between GameObjects.
 * Implementing classes must provide an implementation for the onCollision method.
 * This interface is designed to be used in the context of the Bricker game.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public interface CollisionStrategy {

    /**
     * Handles the collision between two GameObjects.
     *
     * @param object1 the first GameObject involved in the collision.
     * @param object2 the second GameObject involved in the collision.
     */
    void onCollision(GameObject object1, GameObject object2);
}
