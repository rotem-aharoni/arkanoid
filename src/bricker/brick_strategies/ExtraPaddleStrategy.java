package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.util.Counter;

/**
 * ExtraPaddleStrategy is a collision strategy that handles the logic for creating an extra paddle
 * in response to a collision between GameObjects. It extends the BasicCollisionStrategy class.
 * This strategy is specifically designed for use in the Bricker game.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class ExtraPaddleStrategy extends BasicCollisionStrategy {

    /**
     * Constructs an ExtraPaddleStrategy with the specified BrickerGameManager and Counter.
     *
     * @param brickerGameManager the BrickerGameManager instance associated with this strategy.
     * @param brickCount         the Counter instance tracking the number of bricks.
     */
    public ExtraPaddleStrategy(BrickerGameManager brickerGameManager, Counter brickCount) {
        super(brickerGameManager, brickCount);
    }

    /**
     * Handles the collision between two GameObjects. Overrides the method in the superclass
     * to include logic for creating an extra paddle.
     *
     * @param object1 the first GameObject involved in the collision.
     * @param object2 the second GameObject involved in the collision.
     */
    @Override
    public void onCollision(GameObject object1, GameObject object2) {
        super.onCollision(object1, object2);
        brickerGameManager.createExtraPaddle();
    }
}
