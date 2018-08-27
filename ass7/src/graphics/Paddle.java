package graphics;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levels.GameLevel;

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
    private GameLevel gameLevel;

    /**
     * the function check the closest intersection to start of the line.
     * @param keyboard - keyboard object.
     * @param rect - rectangle object.
     * @param color - color.
     * @param speedV - the speed of the paddle.
     * @param gameLevel - the gameLevel.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, java.awt.Color color
            , int speedV, GameLevel gameLevel) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = color;
        this.startLimit = new Point(0, 0);
        this.endLimit = new Point(800, 600);
        this.speedV = speedV;
        this.gameLevel = gameLevel;
    }

    /**
     * the function move the paddle left.
     * @param dt - the dt.
     */
    public void moveLeft(double dt) {
        this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() - ((int) (speedV * dt))
                , this.rect.getUpperLeft().getY()));
    }

    /**
     * the function move the paddle right.
     * @param dt - the dt.
     */
    public void moveRight(double dt) {
        this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() + ((int) (speedV * dt))
                , this.rect.getUpperLeft().getY()));
    }

    /**
     * the function check what key pressed, then change the paddle's position.
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
     * @return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * the function change the position and direction of the ball.
     * @param collisionPoint - the point of the collision.
     * @param currentVelocity - the current velocity.
     * @param ball - the ball.
     */
    public void hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        this.gameLevel.restart();
    }

    /**
     * the function add this paddle to the playgame.
     * @param g the gameLevel.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable((Collidable) this);
        g.addSprite((Sprite) this);
    }
}
