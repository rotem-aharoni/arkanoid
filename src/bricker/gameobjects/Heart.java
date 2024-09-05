package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import static bricker.utils.Constant.PADDLE_TAG_NAME;

/**
 * Heart class represents a heart GameObject in the Bricker game.
 * It extends the GameObject class and includes functionality specific to heart objects.
 * This class maintains a flag indicating whether the heart is taken, and it overrides collision-related
 * methods to specify collision conditions with other GameObjects.
 *
 * @author Rotem Aharoni and Dana Bar Zakay
 */
public class Heart extends GameObject {
    private Boolean isHeartTaken = false;

    /**
     * Constructs a Heart object with the specified top-left corner, dimensions, and renderable.
     *
     * @param topLeftCorner the Vector2 representing the top-left corner position of the heart.
     * @param dimensions    the Vector2 representing the dimensions of the heart.
     * @param renderable    the Renderable object for rendering the heart.
     */
    public Heart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    /**
     * Determines whether the heart should collide with another GameObject.
     * Overrides the base method to allow collision only with objects having the PADDLE_TAG_NAME tag.
     *
     * @param other the other GameObject to check for collision.
     * @return true if the heart should collide with the other GameObject, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return super.shouldCollideWith(other) && other.getTag().equals(PADDLE_TAG_NAME);
    }

    /**
     * Gets the status of whether the heart is taken.
     *
     * @return true if the heart is taken, false otherwise.
     */
    public Boolean getIsHeartTaken() {
        return isHeartTaken;
    }

    /**
     * Handles the behavior when the heart collides with another GameObject.
     * Marks the heart as taken when a collision occurs.
     *
     * @param other     the other GameObject involved in the collision.
     * @param collision the Collision object representing the collision details.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        isHeartTaken = true;
    }
}
