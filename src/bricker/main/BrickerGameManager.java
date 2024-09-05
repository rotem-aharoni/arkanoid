package bricker.main;

import bricker.brick_strategies.CollisionStrategy;
import bricker.brick_strategies.StrategyFactory;
import bricker.gameobjects.*;
import bricker.lives_ui.GraphicUI;
import bricker.lives_ui.LivesUIInterface;
import bricker.lives_ui.NumericUI;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Camera;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
import java.util.Random;

import static bricker.utils.Constant.*;

/**
 * BrickerGameManager class represents the game manager for the Bricker game.
 * It extends the GameManager class and manages the game's initialization, updates, and object interactions.
 * The game includes features such as a paddle, ball, bricks, extra lives, and various collision strategies.
 * It also provides a graphical user interface (UI) for displaying the player's lives.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class BrickerGameManager extends GameManager {
    /**
     * The width of the game board.
     */
    private final static int BOARD_X_SIZE = 700;

    /**
     * The height of the game board.
     */
    private final static int BOARD_Y_SIZE = 500;

    /**
     * The width of the border surrounding the game board.
     */
    private static final int BORDER_WIDTH = 10;

    /**
     * The height of the paddle in the game.
     */
    private static final int PADDLE_HEIGHT = 15;

    /**
     * The width of the paddle in the game.
     */
    private static final int PADDLE_WIDTH = 100;

    /**
     * The height of the bricks in the game.
     */
    private static final int BRICK_HEIGHT = 15;

    /**
     * The radius of the ball in the game.
     */
    private static final int BALL_RADIUS = 20;

    /**
     * The initial speed of the ball in the game.
     */
    private static final int BALL_SPEED = 200;

    /**
     * The speed of the heart object in the game.
     */
    private static final int HEART_SPEED = 100;

    /**
     * The default number of rows for bricks in the game.
     */
    private static final int DEFAULT_BRICKS_ROW = 7;

    /**
     * The default number of bricks in a row in the game.
     */
    private static final int DEFAULT_BRICKS_IN_ROW = 8;

    /**
     * The padding between bricks in the game.
     */
    private static final int BRICK_PADDING = 2;

    /**
     * The width of the heart object in the game.
     */
    private static final int HEART_WIDTH = 15;

    /**
     * The height of the heart object in the game.
     */
    private static final int HEART_HEIGHT = 15;

    /**
     * The padding around the heart object in the game.
     */
    private static final int HEART_PADDING = 3;

    /**
     * The constant representing the initial number of attempts/lives for the player in the game.
     */
    private static final int CONST_ATTEMPTS_NUM = 3;

    /**
     * The number of different brick behavior strategies in the game.
     */
    private static final int BRICKS_BEHAVIOR_NUM = 5;

    /**
     * The number of times the ball must collide before activating the camera in the game.
     */
    private static final int CAMERA_COLLISION_COUNT = 4;

    /**
     * The number of times the extra paddle collide till now.
     */
    private static final int EXTRA_PADDLE_COLLISION_NUM = 4;

    /**
     * The number of times the extra paddle must collide before removing him from the game.
     */
    private final static int MAX_EXTRA_PADDLE_EXIST = 1;


    /**
     * The number of puck objects to create in the game.
     */
    private static final int NUM_OF_PUCKS = 2;

    /**
     * The padding between the game board walls and the game board content.
     */
    private static final int WALLS_PADDING = BORDER_WIDTH * 3;

    /**
     * The initial counter value for brick behavior strategy in the game.
     */
    private static final int STRATEGY_START_COUNTER_VAL = 0;

    /**
     * The prompt message displayed when the player wins the game.
     */
    private static final String WIN_PROMPT = "You Win!";

    /**
     * The prompt message displayed when the player loses the game.
     */
    private static final String LOST_PROMPT = "You lose!";

    /**
     * The prompt message asking the player to play again.
     */
    private static final String PLAY_AGAIN_PROMPT = " Play again?";

    /**
     * The file path for the background image in the game.
     */
    private static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    /**
     * The file path for the brick image in the game.
     */
    private static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * The file path for the ball image in the game.
     */
    private static final String BALL_IMAGE_PATH = "assets/ball.png";

    /**
     * The file path for the ball collision sound in the game.
     */
    private static final String BALL_SOUND_PATH = "assets/blop_cut_silenced.wav";

    /**
     * The file path for the paddle image in the game.
     */
    private static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * The file path for the puck image in the game.
     */
    private static final String PUCK_IMAGE_PATH = "assets/mockBall.png";

    /**
     * The title name for the game window.
     */
    private static final String WINDOWS_TITLE_NAME = "Bricker Game";

    private GameObject[] staticHearts;
    private GameObject[] pucks;
    private Ball ball;
    private TempPaddle tempPaddle;
    private Vector2 windowDimensions;
    private WindowController windowController;
    private UserInputListener inputListener;
    private ImageReader imageReader;
    private SoundReader soundReader;
    private GraphicUI graphicUI;
    private NumericUI numericUI;
    private LivesUIInterface[] liveManger;
    private Counter attemptsNum;
    private Counter brickCount;
    private Counter extraPaddleCount;
    private final int numOfBricksRows;
    private final int numOfBricksCols;
    private int currBallCollisionCount;
    private final Random rand = new Random();

    /**
     * Constructor for BrickerGameManager with default number of bricks rows and columns.
     *
     * @param windowTitle      the title of the game window.
     * @param windowDimensions the dimensions of the game window.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
        this.numOfBricksRows = DEFAULT_BRICKS_ROW;
        this.numOfBricksCols = DEFAULT_BRICKS_IN_ROW;
    }

    /**
     * Constructor for BrickerGameManager with specified number of bricks rows and columns.
     *
     * @param windowTitle      the title of the game window.
     * @param windowDimensions the dimensions of the game window.
     * @param numOfBricksRows  the number of rows for bricks in the game.
     * @param numOfBricksCols  the number of bricks in a row in the game.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions, int numOfBricksRows,
                              int numOfBricksCols) {
        super(windowTitle, windowDimensions);
        this.numOfBricksRows = numOfBricksRows;
        this.numOfBricksCols = numOfBricksCols;
    }

    /**
     * Initializes the game by setting up the game window, input listener, sound reader, and other game
     * objects.
     *
     * @param imageReader      the image reader for loading game assets.
     * @param soundReader      the sound reader for loading game sounds.
     * @param inputListener    the input listener for handling user input.
     * @param windowController the window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {

        this.windowController = windowController;
        this.inputListener = inputListener;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.staticHearts = new Heart[MAX_LIVES_COUNT];
        this.pucks = new Puck[NUM_OF_PUCKS];
        this.attemptsNum = new Counter(CONST_ATTEMPTS_NUM);
        this.windowDimensions = windowController.getWindowDimensions();
        this.brickCount = new Counter(numOfBricksRows * numOfBricksCols);
        this.graphicUI = new GraphicUI(imageReader, HEART_WIDTH + HEART_PADDING, this);
        this.numericUI = new NumericUI();
        this.liveManger = new LivesUIInterface[]{graphicUI, numericUI};
        this.extraPaddleCount = new Counter(0);


        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        initBackground();
        initWalls();
        initBall();
        initPaddle();
        initBricks();
        initNumericLives();
        initGraphicLives();
    }

    /**
     * Updates the game state based on the elapsed time since the last update.
     *
     * @param deltaTime the time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float ballHeight = ball.getCenter().y();
        if (inputListener.isKeyPressed(KeyEvent.VK_W)) {
            brickCount.reset();
        }

        checkIfBallFall(ballHeight);
        checkForWin(ballHeight);
        checkIfNeedRemoveTempPaddle();
        checkIfNeedRemoveCamera();
        checkIfObjectFall(pucks);
        checkIfHeartCatch();
        checkIfHeartFall();

        for (LivesUIInterface livesUIInterface : liveManger) {
            livesUIInterface.updateLives(this.attemptsNum);
        }
    }

    /**
     * Checks if any game object has fallen below the window dimensions and removes it if necessary.
     *
     * @param objects the array of game objects to check.
     */
    private void checkIfObjectFall(GameObject[] objects) {
        for (GameObject object : objects) {
            if (object != null && object.getCenter().y() > windowDimensions.y()) {
                gameObjects().removeGameObject(object);
            }
        }
    }

    /**
     * Checks if a dynamic heart object has fallen below the window dimensions and removes it if necessary.
     */
    private void checkIfHeartFall() {
        for (GameObject object : super.gameObjects()) {
            if (object.getTag().equals(DYNAMIC_HEART_TAG) && object.getCenter().y() > windowDimensions.y()) {
                gameObjects().removeGameObject(object);
            }
        }
    }

    /**
     * Checks if the player has caught a dynamic heart, increments attempts, and updates the game state.
     */
    private void checkIfHeartCatch() {
        for (GameObject gameObject : super.gameObjects()) {
            if (gameObject.getTag().equals(DYNAMIC_HEART_TAG) && attemptsNum.value() < MAX_LIVES_COUNT &&
                    ((Heart) gameObject).getIsHeartTaken()) {
                attemptsNum.increment();
                gameObjects().removeGameObject(gameObject);
                gameObjects().addGameObject(staticHearts[attemptsNum.value() - 1], Layer.UI);
            }
        }
    }

    /**
     * Checks if the ball has fallen below the window dimensions, decrements attempts, and resets its
     * position.
     *
     * @param ballHeight the height of the ball.
     */
    private void checkIfBallFall(float ballHeight) {
        if (ballHeight > windowDimensions.y()) {
            attemptsNum.decrement();
            locateBallOnBoard(ball);
            ball.setCenter(windowDimensions.mult(0.5f));
        }
    }

    /**
     * Checks if the temporary paddle needs to be removed and removes it if necessary.
     */
    public void checkIfNeedRemoveTempPaddle() {
        if (tempPaddle != null && tempPaddle.checkIfPaddleWorkEnd()) {
            extraPaddleCount.reset();
            gameObjects().removeGameObject(tempPaddle);
        }
    }

    /**
     * Checks if the camera needs to be removed based on the ball's collision count.
     */
    public void checkIfNeedRemoveCamera() {
        if (camera() != null && ball.getCollisionCounter() - currBallCollisionCount ==
                CAMERA_COLLISION_COUNT) {
            setCamera(null);
        }
    }

    /**
     * Checks for win or loss conditions and displays the corresponding prompt.
     *
     * @param ballHeight the height of the ball.
     */
    private void checkForWin(float ballHeight) {
        String prompt = "";
        if (brickCount.value() == 0) {
            // you win
            prompt = WIN_PROMPT;
        }
        if (ballHeight > windowDimensions.y() && attemptsNum.value() == 0) {
            // you lost
            prompt = LOST_PROMPT;
        }
        if (!prompt.isEmpty()) {
            prompt += PLAY_AGAIN_PROMPT;
            if (windowController.openYesNoDialog(prompt)) {

                windowController.resetGame();
            } else {
                windowController.closeWindow();
            }
        }
    }

    /**
     * Initializes the background of the game with the specified background image.
     */
    private void initBackground() {
        Renderable backgroundImage = imageReader.readImage(BACKGROUND_IMAGE_PATH, false);
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions, backgroundImage);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /**
     * Initializes the ball object with the specified ball image and collision sound.
     */
    private void initBall() {
        Renderable ballImage = imageReader.readImage(BALL_IMAGE_PATH, true);
        Sound collisionSound = soundReader.readSound(BALL_SOUND_PATH);
        ball = new Ball(Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS), ballImage, collisionSound);
        locateBallOnBoard(ball);
        ball.setCenter(windowDimensions.mult(0.5f));
        ball.setTag(BALL_TAG_NAME);
        gameObjects().addGameObject(ball);
    }

    /**
     * Locates the ball on the game board and sets its initial velocity.
     *
     * @param object the ball object.
     */
    private void locateBallOnBoard(GameObject object) {
        float ballVelY = BALL_SPEED;
        float ballVelX = BALL_SPEED;
        if (rand.nextBoolean()) {
            ballVelX *= -1;
        }
        if (rand.nextBoolean()) {
            ballVelY *= -1;
        }
        object.setVelocity(new Vector2(ballVelX, ballVelY));
    }

    /**
     * Initializes the paddle object with the specified paddle image and input listener.
     */
    private void initPaddle() {
        Renderable paddleImage = imageReader.readImage(PADDLE_IMAGE_PATH, true);
        Vector2 newWindowDimensions = new Vector2(windowDimensions.x(), windowDimensions.y() - WALLS_PADDING);
        GameObject paddle = new Paddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), paddleImage,
                inputListener, newWindowDimensions);
        paddle.setTopLeftCorner(new Vector2(windowDimensions.x() / 2, windowDimensions.y() - WALLS_PADDING));
        paddle.setTag(PADDLE_TAG_NAME);
        gameObjects().addGameObject(paddle);
    }

    /**
     * Creates an extra paddle if the maximum limit is not reached.
     */
    public void createExtraPaddle() {
        if (extraPaddleCount.value() < MAX_EXTRA_PADDLE_EXIST) {
            Renderable paddleImage = imageReader.readImage(PADDLE_IMAGE_PATH, true);
            Vector2 newWindowDimensions = new Vector2(windowDimensions.x(), windowDimensions.y() / 2);
            tempPaddle = new TempPaddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), paddleImage,
                    inputListener, newWindowDimensions, new Counter(EXTRA_PADDLE_COLLISION_NUM));
            tempPaddle.setTopLeftCorner(new Vector2(windowDimensions.x() / 2, windowDimensions.y() / 2));
            gameObjects().addGameObject(tempPaddle);
            extraPaddleCount.increment();
        }
    }

    /**
     * Creates an extra life (dynamic heart) at the specified location.
     *
     * @param location the location where the extra life should be created.
     */
    public void createExtraLife(Vector2 location) {
        Renderable heartImage = imageReader.readImage(HEART_IMAGE_PATH, true);
        Heart dynamicHeart = new Heart(location, new Vector2(HEART_WIDTH, HEART_HEIGHT), heartImage);
        dynamicHeart.setTag(DYNAMIC_HEART_TAG);
        dynamicHeart.setVelocity(Vector2.DOWN.mult(HEART_SPEED));
        gameObjects().addGameObject(dynamicHeart);
    }

    /**
     * Initializes the game board walls (left, right, and upper walls).
     */
    private void initWalls() {
        GameObject leftWall = new GameObject(Vector2.ZERO, new Vector2(BORDER_WIDTH, BOARD_Y_SIZE), null);
        gameObjects().addGameObject(leftWall);
        GameObject rightWall = new GameObject(new Vector2(BOARD_X_SIZE - BORDER_WIDTH, 0), new
                Vector2(BORDER_WIDTH, BOARD_Y_SIZE), null);
        gameObjects().addGameObject(rightWall);
        GameObject upperWall = new GameObject(Vector2.ZERO, new Vector2(BOARD_X_SIZE, BORDER_WIDTH), null);
        gameObjects().addGameObject(upperWall);
    }

    /**
     * Initializes the bricks on the game board with the specified brick image and behavior strategy.
     */
    private void initBricks() {
        Renderable brickImage = imageReader.readImage(BRICK_IMAGE_PATH, false);
        int brickWidth = (BOARD_X_SIZE - BORDER_WIDTH * 2 - (BRICK_PADDING * numOfBricksCols - 1)) /
                numOfBricksCols;

        for (int i = 0; i < numOfBricksRows; i++) {
            for (int j = 0; j < numOfBricksCols; j++) {
                int brickX = BORDER_WIDTH + j * (brickWidth + BRICK_PADDING);
                int brickY = BORDER_WIDTH + i * (BRICK_HEIGHT + BRICK_PADDING);
                Vector2 brickPos = new Vector2(brickX, brickY);
                GameObject brick = new Brick(brickPos, new Vector2(brickWidth, BRICK_HEIGHT), brickImage,
                        selectStrategyForBrick());
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            }
        }
    }

    /**
     * Selects a random behavior strategy for a brick using the StrategyFactory.
     *
     * @return the selected CollisionStrategy for the brick.
     */
    private CollisionStrategy selectStrategyForBrick() {
        StrategyFactory strategyFactory = new StrategyFactory(this, brickCount);
        if (rand.nextBoolean()) {
            return strategyFactory.buildRenderer(BASIC_STRATEGY, STRATEGY_START_COUNTER_VAL);
        } else {
            int index = rand.nextInt(BRICKS_BEHAVIOR_NUM);
            return strategyFactory.buildRenderer(index, STRATEGY_START_COUNTER_VAL);
        }
    }

    /**
     * Selects a normal behavior strategy for a brick using the StrategyFactory.
     *
     * @param brickCount the Counter representing the number of remaining bricks.
     * @return the selected CollisionStrategy for the brick.
     */
    public CollisionStrategy selectNormalStrategyBehavior(Counter brickCount) {
        StrategyFactory strategyFactory = new StrategyFactory(this, brickCount);
        int index = rand.nextInt(DOUBLE_BEHAVIOR_STRATEGY);
        return strategyFactory.buildRenderer(index, STRATEGY_START_COUNTER_VAL);
    }

    /**
     * Initializes the numeric UI for displaying the player's remaining lives.
     */
    private void initNumericLives() {
        numericUI.setTopLeftCorner(new Vector2(BORDER_WIDTH, this.windowDimensions.y() - BORDER_WIDTH * 3));
        numericUI.setDimensions(new Vector2(HEART_WIDTH, HEART_HEIGHT));
        numericUI.createObject(attemptsNum.value());
        GameObject text = numericUI.getSpecificObject(0);
        gameObjects().addGameObject(text, Layer.UI);
    }

    /**
     * Initializes the graphic UI for displaying the player's remaining lives.
     */
    private void initGraphicLives() {
        graphicUI.setTopLeftCorner(new Vector2(BORDER_WIDTH + HEART_WIDTH + HEART_PADDING,
                windowDimensions.y() - WALLS_PADDING));
        graphicUI.setDimensions(new Vector2(HEART_WIDTH, HEART_HEIGHT));
        graphicUI.createObject(MAX_LIVES_COUNT);

        for (int i = 0; i < MAX_LIVES_COUNT; i++) {
            staticHearts[i] = graphicUI.getSpecificObject(i);
        }

        for (int i = 0; i < attemptsNum.value(); i++) {
            gameObjects().addGameObject(staticHearts[i], Layer.UI);
        }
    }

    /**
     * Creates a camera to follow the ball's movements.
     */
    public void createCamera() {
        currBallCollisionCount = ball.getCollisionCounter();
        setCamera(new Camera(ball, Vector2.ZERO, windowDimensions.mult(1.2f), windowDimensions));
    }

    /**
     * Creates multiple pucks at the specified location.
     *
     * @param location The location where the pucks should be created.
     */
    public void createPucks(Vector2 location) {
        Renderable ballImage = imageReader.readImage(PUCK_IMAGE_PATH, true);
        Sound collisionSound = soundReader.readSound(BALL_SOUND_PATH);
        Vector2 dimension = new Vector2(BALL_RADIUS * ((float) 0.75), BALL_RADIUS * ((float) 0.75));

        for (int i = 0; i < NUM_OF_PUCKS; i++) {
            pucks[i] = new Puck(location, dimension, ballImage, collisionSound);
            locateBallOnBoard(pucks[i]);
            pucks[i].setTag(PUCK_TAG_NAME);
            gameObjects().addGameObject(pucks[i]);
        }
    }

    /**
     * Removes the specified game object from the game world.
     *
     * @param object    the game object to be removed.
     * @param dataLayer the layer of the game object.
     */
    public void removeObject(GameObject object, final int dataLayer) {
        gameObjects().removeGameObject(object, dataLayer);
    }

    /**
     * Main method to run the BrickerGameManager, initializing the game and starting the game loop.
     *
     * @param args the command line arguments. Accepts optional arguments for custom board size.
     */
    public static void main(String[] args) {
        BrickerGameManager brickerGameManager;
        if (args.length == 2) {
            brickerGameManager = new BrickerGameManager(WINDOWS_TITLE_NAME,
                    new Vector2(BOARD_X_SIZE, BOARD_Y_SIZE), Integer.parseInt(args[1]),
                    Integer.parseInt(args[0]));
        } else {
            brickerGameManager = new BrickerGameManager(WINDOWS_TITLE_NAME,
                    new Vector2(BOARD_X_SIZE, BOARD_Y_SIZE));
        }
        brickerGameManager.run();
    }
}
