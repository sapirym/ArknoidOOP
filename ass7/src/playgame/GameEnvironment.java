package playgame;

import graphics.Collidable;
import graphics.CollisionInfo;
import graphics.Line;
import graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * A GameEnviroment class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-20
 */

public class GameEnvironment {
    private List<Collidable> collidableList = new ArrayList<Collidable>();

    /**
     * constructor for playgame.GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * the function add the given collidable to the environment.
     *
     * @param c graphics.Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * the function gets the closest collision that occurs.
     *
     * @param trajectory line to check the closets collision.
     * @return graphics.CollisionInfo - information about the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> pointListIntersection = new ArrayList<Point>();
        List<Collidable> newListCollidable = new ArrayList<Collidable>(this.collidableList);
        Point intersectPoint;
        int counter = -1;
        for (Collidable i : newListCollidable) {
            pointListIntersection.add(trajectory.
                    closestIntersectionToStartOfLine((i.getCollisionRectangle())));
        }
        double distanceMin = Double.MAX_VALUE;
        double distanceNext;
        Point minDistancePoint = null;
        for (int j = 0; j < pointListIntersection.size(); j++) {
            if (pointListIntersection.get(j) != null) {
                distanceNext = trajectory.getEnd().distance(pointListIntersection.get(j));
                if (distanceMin > distanceNext) {
                    distanceMin = distanceNext;
                    counter = j;
                }
            }
        }
        if (counter != -1) {
            return new CollisionInfo(pointListIntersection.get(counter), this.collidableList.get(counter));
        }
        return null;
    }

    /**
     * the function remove collidable object from the list of collidables.
     *
     * @param collidable the collidable object to remove.
     * @return list of cllidables.
     */
    public List<Collidable> removeCollidable(Collidable collidable) {
        if (this.collidableList.contains(collidable)) {
            this.collidableList.remove(collidable);
        }
        return collidableList;
    }
}
