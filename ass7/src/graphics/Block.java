package graphics;

import biuoop.DrawSurface;
import levels.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * graphics.Block class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */


public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private Integer hits;
    private List<HitListener> hitListeners;
    private Image image;
    private Color drawColor;

    /**
     * constructor for block.
     *
     * @param rect  - the rectangle.
     * @param color - the color o the block.
     * @param hits - number of hits for block.
     * @param drawClr - color to draw the block.
     */
    public Block(Rectangle rect, Color color, Integer hits, Color drawClr) {
        this.hitListeners = new ArrayList<HitListener>();
        this.rectangle = rect;
        this.drawColor = drawClr;
        this.image = null;
        this.color = color;
        this.hits = hits;
    }

    /**
     * constructor for block.
     *
     * @param rect  - the rectangle.
     * @param img - the image of the block.
     * @param hitsNum - number of hits for block.
     */
    public Block(Rectangle rect, Image img, Integer hitsNum) {
        this.rectangle = rect;
        hitListeners = new ArrayList<HitListener>();
        this.drawColor = null;
        this.image = img;
        this.color = null;
        this.hits = hitsNum;
    }
    /**
     * get the hit points.
     *
     * @return the hit points.
     */
    public int getHitPoints() {
        return hits;
    }
    /**
     * the function get the rectangle.
     *
     * @return rect the shape of the ball.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the hit listener object.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hit listener object.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify to all the listeners about the hit.
     *
     * @param hitter - the ball that hits.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * the function change the position and direction of the ball.
     *
     * @param collisionPoint  the point of th collision.
     * @param currentVelocity the current velocity.
     * @param hitter          the ball hitter.
     */
    public void hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (hits != null && hits > 0) {
            this.hits -= 1;
        }
        this.notifyHit(hitter);
    }

    /**
     * the function draws the block.
     *
     * @param surface the draw surface.
     */
    public void drawOn(DrawSurface surface) {
        try {
            if (image == null) {
                surface.setColor(this.color);
                surface.fillRectangle((int) this.rectangle.getUpperLeft().getX()
                        , (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

            } else {
                surface.drawImage((int) this.rectangle.getUpperLeft().getX()
                        , (int) this.rectangle.getUpperLeft().getY(),
                        this.image);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: problem with 'surface'.");
        }
    }

    /**
     * the function for now does'nt do nothing for now.
     *
     * @param dt - the dt.
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * the function add the sprite and collidable items to the
     * list of sprites of the playgame.
     *
     * @param g the playgame object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable((Collidable) this);
        g.addSprite((Sprite) this);
    }

    /**
     * the function add remove sprite and collidable items from the
     * list of sprites of the playgame.
     *
     * @param game the gameLevel object.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * This method returns the number of the times that the block hatted.
     *
     * @return number of the events that it's happened.
     */
    public Integer getHits() {
        return this.hits;
    }

    /**
     * Method that returns the rectangle of the block.
     *
     * @return the rectangle of the block.
     */
    public Rectangle getRect() {
        return this.rectangle;
    }

    /**
     * Method for set the rectangle of the block.
     *
     * @param newRect the rectangle to set.
     */
    public void setRectangle(Rectangle newRect) {
        this.rectangle = newRect;
    }

    /**
     * check if the block is alien or shield.
     *
     * @return boolean value.
     */
    public boolean alienType() {
        return false;
    }
}
