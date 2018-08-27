package graphics;

/**
 * An graphics.Velocity class.
 * <p>
 * 316251818
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 2.0
 * @since 2017-04-04
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor of graphics.Velocity.
     *
     * @param dx the x difference.
     * @param dy the y difference.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take an angle and speed and change the velocity.
     *
     * @param angle radians.
     * @param speed the speed of the ball.
     * @return a new point with position (x+dx, y+dy).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //change to radian
        double dx = Math.round(speed * Math.sin(Math.toRadians(angle)));
        double dy = Math.round((-1) * speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * get the dx value.
     *
     * @return dx value.
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * get the dy value.
     *
     * @return dy value.
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p  point to change velocity.
     * @param dt -the dt.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(Math.round(p.getX() + this.getDX() * dt), Math.round(p.getY() + this.getDY() * dt));
    }

    /**
     * the function set the dx.
     *
     * @param dX the difference of x.
     */
    public void setDx(double dX) {
        this.dx = dX;
    }

    /**
     * the function set the dy.
     *
     * @param dY the difference of y.
     */
    public void setDy(double dY) {
        this.dy = dY;
    }
}
