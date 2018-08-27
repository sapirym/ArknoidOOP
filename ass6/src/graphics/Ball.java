package graphics;

import biuoop.DrawSurface;
import playgame.GameEnvironment;
import playgame.GameLevel;

import java.awt.Color;

/**
 * A graphics.Ball class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-10
 */

public class Ball implements Sprite {
    private Point center;
    private Point limitStart = new Point(0, 0);
    private Point limitEnd = new Point(800, 600);
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * constructor for graphics.Ball.
     *
     * @param center      the center point of the ball.
     * @param r           the radius of the ball.
     * @param color       the color of the ball.
     * @param limitStart  the start of the frame.
     * @param limitEnd    the end of the frame.
     * @param environment the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, Point limitStart,
                Point limitEnd, GameEnvironment environment) {
        this.center = center;
        this.color = color;
        this.r = r;
        //this.limitStart = limitStart;
        //this.limitEnd = limitEnd;
        this.gameEnvironment = environment;
    }

    /**
     * constructor for graphics.Ball.
     *
     * @param x          the value of x of the center point.
     * @param y          the value of y of the center point.
     * @param r          the radius of the ball.
     * @param color      the color of the ball.
     * @param limitStart the start of the frame.
     * @param limitEnd   the end of the frame.
     */
    public Ball(int x, int y, int r, java.awt.Color color,
                Point limitStart, Point limitEnd) {
        this.color = color;
        this.r = r;
        this.center = new Point(x, y);
        //this.limitStart = limitStart;
        //this.limitEnd = limitEnd;
    }
    /**
     * constructor for graphics.Ball.
     *
     * @param x          the value of x of the center point.
     * @param y          the value of y of the center point.
     * @param r          the radius of the ball.
     * @param givenColor     the color of the ball.
     * @param environment    the evironment.
     */
    public Ball(double x, double y, int r, java.awt.Color givenColor, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.r = r;
        color = givenColor;
        this.gameEnvironment = environment;
    }

    /**
     * the method set the environment of the game.
     *
     * @param environment the game environment.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * the method set the velocity of the object.
     *
     * @param dx the x difference.
     * @param dy the y difference.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * return the x value of the center point.
     *
     * @return the x value of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * return the y value of the center point.
     *
     * @return the y value of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return the velocity of ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * the method set the velocity of the object.
     *
     * @param v the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * changes the direction of the ball if it get the boundaries,
     * and the center value of the ball.
     *
     * @param dt - the dt.
     */
    public void moveOneStep(double dt) {
        Point pointAfterStep = this.velocity.applyToPoint(this.center, dt);
        double dx = this.velocity.getDX();
        double dy = this.velocity.getDY();
        Velocity v = new Velocity(dx, dy);
        pointAfterStep = v.applyToPoint(pointAfterStep, dt);
        Line trajectory = new Line(Math.round(this.center.getX()), Math.round(this.center.getY()),
                Math.round(pointAfterStep.getX()), Math.round(pointAfterStep.getY()));
        if (this.gameEnvironment.getClosestCollision(trajectory) != null) {
            CollisionInfo closeCollision = this.gameEnvironment.getClosestCollision(trajectory);
            this.setVelocity(closeCollision.collisionObject()
                    .hit(this, closeCollision.collisionPoint(), this.velocity));
        }
        this.center = this.velocity.applyToPoint(this.center, dt);

    }

    /**
     * return the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * return the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the function draws the ball.
     *
     * @param surface the draw surface.
     */
    public void drawOn(DrawSurface surface) {
        try {
            surface.setColor(Color.BLACK);
            surface.fillCircle((int) center.getX(), (int) center.getY(), r);
            surface.setColor(this.color);
            surface.drawCircle((int) center.getX(), (int) center.getY(), r);
        } catch (Exception e) {
            System.out.println("Error: problem with 'surface'.");
        }
    }

    /**
     * the function call moveOneStep every difference of time.
     *
     * @param dt - the dt.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * the function add the sprite item to the list of sprites of the game.
     *
     * @param g the game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite((Sprite) this);
    }

    /**
     * the function remove the sprite item from the list of sprites of the game.
     *
     * @param g the game object.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}