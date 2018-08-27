package graphics;

/**
 * A graphics.Point class.
 * <p>
 * 316251818
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 2.0
 * @since 2017-04-04
 */

public class Point {
    private double x;
    private double y;

    /**
     * Construct a point givne x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param other a point to measure the distance to.
     * @return the distance to the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other a point to check if equal.
     * @return boolean- true if equals, false is not.
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }
}

