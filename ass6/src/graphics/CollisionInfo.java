package graphics;

/**
 * A Collisioninfo class- information about the collision.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-10
 */

public class CollisionInfo {
    private Point collisionPoint;
    private Collidable object;

    /**
     * constructor for Collisioninfo.
     *
     * @param collisionPoint - the point at which the collision occurs.
     * @param object         - the object that make the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable object) {
        this.collisionPoint = collisionPoint;
        this.object = object;
    }

    /**
     * the function Return the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */

    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * the function Return the collidable object involved in the collision.
     *
     * @return Return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return object;
    }
}
