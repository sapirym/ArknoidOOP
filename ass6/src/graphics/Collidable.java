package graphics;

/**
 * A graphics.Collidable interface.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-28
 */

public interface Collidable {
    /**
     * the function Return the "collision shape" of the object.
     *
     * @return Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * the function Notify the object that we collided with it
     * at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the current velocity.
     * @param hitter          - the ball that hits.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
