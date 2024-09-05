package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.util.Counter;

import static bricker.utils.Constant.BALL_TAG_NAME;

/**
 * ChangeCameraStrategy is a collision strategy that handles the logic for changing the camera
 * in response to a collision between GameObjects. It extends the BasicCollisionStrategy class.
 * This strategy is specifically designed for use in the Bricker game.
 * When a collision occurs between GameObjects, this strategy checks if the game manager's camera
 * is currently null and if the collision involves a GameObject with the tag BALL_TAG_NAME.
 * If these conditions are met, it triggers the creation of a new camera using the game manager.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class ChangeCameraStrategy extends BasicCollisionStrategy {

    /**
     * Constructs a ChangeCameraStrategy with the specified BrickerGameManager and Counter.
     *
     * @param brickerGameManager the BrickerGameManager instance associated with this strategy.
     * @param brickCount         the Counter instance tracking the number of bricks.
     */
    public ChangeCameraStrategy(BrickerGameManager brickerGameManager, Counter brickCount) {
        super(brickerGameManager, brickCount);
    }


    /**
     * Handles the collision between two GameObjects. Overrides the method in the superclass
     * to include logic for changing the camera when specific conditions are met.
     *
     * @param object1 the first GameObject involved in the collision.
     * @param object2 the second GameObject involved in the collision.
     */
    @Override
    public void onCollision(GameObject object1, GameObject object2) {
        super.onCollision(object1, object2);

        // Check if the game manager's camera is null and if the collision involves a ball
        if (brickerGameManager.camera() == null && object2.getTag().equals(BALL_TAG_NAME)) {
            // Create a new camera using the game manager
            brickerGameManager.createCamera();
        }
    }
}
