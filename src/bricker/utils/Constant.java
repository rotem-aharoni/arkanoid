package bricker.utils;

/**
 * Constant class contains constant values used in the Bricker game.
 * This class provides a centralized location for managing constant values, such as tag names, file paths, and
 * game settings.
 * The constants include tags for various game objects, file paths for assets, and numerical values for game
 * strategies.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class Constant {
    /**
     * The tag name assigned to the main ball in the game.
     */
    public static final String BALL_TAG_NAME = "mainBall";

    /**
     * The tag name assigned to the main paddle in the game.
     */
    public static final String PADDLE_TAG_NAME = "mainPaddle";

    /**
     * The tag name assigned to pucks in the game.
     */
    public static final String PUCK_TAG_NAME = "Puck";

    /**
     * The tag name assigned to dynamic hearts in the game.
     */
    public static final String DYNAMIC_HEART_TAG = "dynamicHeart";

    /**
     * The file path for the heart image used in the game.
     */
    public static final String HEART_IMAGE_PATH = "assets/heart.png";

    /**
     * The maximum number of lives in the game.
     */
    public static final int MAX_LIVES_COUNT = 4;

    /**
     * The strategy code for the pack strategy in the game.
     */
    public static final int PACK_STRATEGY = 0;

    /**
     * The strategy code for the extra paddle strategy in the game.
     */
    public static final int EXTRA_PADDLE_STRATEGY = 1;

    /**
     * The strategy code for changing the camera strategy in the game.
     */
    public static final int CHANGE_CAMARA_STRATEGY = 2;

    /**
     * The strategy code for the extra life strategy in the game.
     */
    public static final int EXTRA_LIFE_STRATEGY = 3;

    /**
     * The strategy code for the double behavior strategy in the game.
     */
    public static final int DOUBLE_BEHAVIOR_STRATEGY = 4;

    /**
     * The strategy code for the basic strategy in the game.
     */
    public static final int BASIC_STRATEGY = 5;

    /**
     * Default constructor for the Constant class.
     * Note: This constructor is automatically generated since no explicit constructor is defined.
     */
    public Constant() {
        // No explicit logic is needed for the default constructor.
    }

}
