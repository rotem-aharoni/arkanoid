package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.util.Counter;

/**
 * ExtraLifeStrategy is a collision strategy that handles the logic for creating an extra life
 * in response to a collision between GameObjects. It extends the BasicCollisionStrategy class.
 * This strategy is specifically designed for use in the Bricker game.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class ExtraLifeStrategy extends BasicCollisionStrategy {

    /**
     * Constructs an ExtraLifeStrategy with the specified BrickerGameManager and Counter.
     *
     * @param brickerGameManager the BrickerGameManager instance associated with this strategy.
     * @param brickCount         the Counter instance tracking the number of bricks.
     */
    public ExtraLifeStrategy(BrickerGameManager brickerGameManager, Counter brickCount) {
        super(brickerGameManager, brickCount);
    }

    /**
     * Handles the collision between two GameObjects. Overrides the method in the superclass
     * to include logic for creating an extra life.
     *
     * @param object1 the first GameObject involved in the collision.
     * @param object2 the second GameObject involved in the collision.
     */
    @Override
    public void onCollision(GameObject object1, GameObject object2) {
        super.onCollision(object1, object2);
        brickerGameManager.createExtraLife(object1.getCenter());
    }
}
