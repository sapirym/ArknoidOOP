package graphics;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import playgame.GameLevel;

import java.awt.Color;

/**
 * A paddle class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-28
 */

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private Point startLimit;
    private Point endLimit;
    private int speedV;

    /**
     * the function check the closest intersection to start of the line.
     *
     * @param keyboard   keyboard object.
     * @param rect       rectangle object.
     * @param color      color.
     * @param startLimit the start limit.
     * @param endLimit   the end limit.
     * @param speedV     the speed of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, java.awt.Color color, Point startLimit
            , Point endLimit, int speedV) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = color;
        this.startLimit = startLimit;
        this.endLimit = endLimit;
        this.speedV = speedV;
    }

    /**
     * the function move the paddle left.
     *
     * @param dt - the dt.
     */
    public void moveLeft(double dt) {
        this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() - ((int) (speedV * dt))
                , this.rect.getUpperLeft().getY()));
    }

    /**
     * the function move the paddle right.
     *
     * @param dt - the dt.
     */
    public void moveRight(double dt) {
        this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() + ((int) (speedV * dt))
                , this.rect.getUpperLeft().getY()));
    }

    /**
     * the function check what key pressed, then change the paddle's position.
     *
     * @param dt - the dt.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if ((this.rect.getUpperLeft().getX() - speedV * dt - 25.1) >= this.startLimit.getX()) {
                moveLeft(dt);
            }
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if ((this.rect.getUpperLeft().getX() + speedV * dt + 29 + this.rect.getWidth()) < this.endLimit.getX()) {
                moveRight(dt);
            }
        } else {
            return;
        }
    }

    /**
     * the function draws the paddle.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * the function return the rectangle position.
     *
     * @return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * the function change the position and direction of the ball.
     *
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the current velocity.
     * @param ball            - the ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        double speed = Math.sqrt(
                currentVelocity.getDX() * currentVelocity.getDX()
                        + currentVelocity.getDY() * currentVelocity.getDY());
        Velocity newVelocity = currentVelocity;

        double startX = this.rect.getUpperLeft().getX();
        double startY = this.rect.getUpperLeft().getY();

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        if (collisionPoint.equals(this.rect.getUpperLeft())
                || collisionPoint.equals(this.rect.getRightUpperPoint())
                || collisionPoint.equals(this.rect.getLeftLowerPoint())
                || collisionPoint.equals(this.rect.getRightLowerPoint())) {
            newVelocity.setDx(currentVelocity.getDX() * (-1));
            newVelocity.setDy(currentVelocity.getDY() * (-1));
        } else if ((x <= (startX + this.rect.getWidth() / 5.0))) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if ((x <= startX + 2 * this.rect.getWidth() / 5.0)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if ((x <= startX + 3 * this.rect.getWidth() / 5.0)) {
            return Velocity.fromAngleAndSpeed(360, speed);
        } else if ((x <= startX + 4 * this.rect.getWidth() / 5.0)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if ((x <= startX + this.rect.getWidth())) {
            return Velocity.fromAngleAndSpeed(60, speed);
        } else if ((y > (startY + this.rect.getHeight()))) {
            newVelocity.setDx(currentVelocity.getDX() * (-1));
        } else {
            newVelocity.setDy((currentVelocity.getDY()) * (-1));
        }
        return newVelocity;
    }

    /**
     * the function add this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable((Collidable) this);
        g.addSprite((Sprite) this);
    }
}
