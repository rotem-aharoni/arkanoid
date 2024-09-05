package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.util.Counter;

import java.util.Random;

import static bricker.utils.Constant.*;

/**
 * StrategyFactory class is responsible for creating instances of CollisionStrategy based on strategy indices.
 * It provides methods for building different collision strategies used in the Bricker game.
 * The strategies include Pack Strategy, Extra Paddle Strategy, Change Camera Strategy,
 * Extra Life Strategy, Double Behavior Strategy, and Basic Collision Strategy.
 * The factory also handles the creation of double behavior strategies by combining two different strategies.
 * It ensures that the double behavior strategy is not created indefinitely by setting a maximum count.
 * StrategyFactory relies on the BrickerGameManager and a Counter to manage game state and behavior.
 * Additionally, this class defines constants related to strategy indices and behavior counts.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class StrategyFactory {
    /**
     * The maximum count for double behavior strategies to prevent indefinite creation.
     */
    private static final int MAX_DOUBLE_BEHAVIOR_COUNT = 3;

    private final BrickerGameManager brickerGameManager;

    private final Counter brickCount;

    /**
     * Constructs a StrategyFactory object with the specified BrickerGameManager and Counter.
     *
     * @param brickerGameManager the BrickerGameManager responsible for managing the game state
     * @param brickCount         the Counter representing the count of bricks in the game
     */
    public StrategyFactory(BrickerGameManager brickerGameManager, Counter brickCount) {
        this.brickerGameManager = brickerGameManager;
        this.brickCount = brickCount;
    }

    /**
     * Builds and returns a CollisionStrategy based on the provided index and count of strategies.
     *
     * @param index         the index representing the desired strategy
     * @param countStrategy the count of strategies used to create double behavior strategies
     * @return a CollisionStrategy instance based on the specified index
     */
    public CollisionStrategy buildRenderer(int index, int countStrategy) {
        switch (index) {
            case PACK_STRATEGY:
                return new PuckStrategy(brickerGameManager, brickCount);
            case EXTRA_PADDLE_STRATEGY:
                return new ExtraPaddleStrategy(brickerGameManager, brickCount);
            case CHANGE_CAMARA_STRATEGY:
                return new ChangeCameraStrategy(brickerGameManager, brickCount);
            case EXTRA_LIFE_STRATEGY:
                return new ExtraLifeStrategy(brickerGameManager, brickCount);
            case DOUBLE_BEHAVIOR_STRATEGY:
                CollisionStrategy behavior1 = brickerGameManager.selectNormalStrategyBehavior(brickCount);
                countStrategy++;
                CollisionStrategy behavior2 = doubleBehaviourStrategy(countStrategy);
                return new DoubleBehaviorStrategy(brickCount, behavior1, behavior2);
            default:
                return new BasicCollisionStrategy(brickerGameManager, brickCount);
        }
    }

    /**
     * Creates and returns a double behavior strategy based on a random index and count of strategies.
     *
     * @param countStrategy the count of strategies used to create double behavior strategies
     * @return a CollisionStrategy instance representing a double behavior strategy
     */
    public CollisionStrategy doubleBehaviourStrategy(int countStrategy) {
        Random rand = new Random();
        int index = rand.nextInt(DOUBLE_BEHAVIOR_STRATEGY + 1);
        if (index != DOUBLE_BEHAVIOR_STRATEGY) {
            return buildRenderer(index, countStrategy + 1);
        }
        if (countStrategy < MAX_DOUBLE_BEHAVIOR_COUNT) {
            return buildRenderer(index, countStrategy + 1);
        }
        return brickerGameManager.selectNormalStrategyBehavior(brickCount);
    }
}
