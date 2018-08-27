package graphics;

import biuoop.DrawSurface;
import listeners.HitListener;
import listeners.HitNotifier;
import playgame.GameLevel;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * graphics.Block class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-28
 */

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private int hits;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private TreeMap<Integer, String> fillsMap;
    private Color stroke;
    private TreeMap<Integer, Image> imageFillMap;
    private TreeMap<Integer, Color> colorsFillMap;

    /**
     * constructor for block.
     *
     * @param rect  - the rectangle.
     * @param color - the color o the block.
     * @param hits  number of hits for block.
     */
    public Block(Rectangle rect, Color color, int hits) {
        this.rect = rect;
        this.color = color;
        this.hits = hits;
        this.fillsMap = null;
        this.stroke = stroke;
        this.colorsFillMap = null;
        this.imageFillMap = null;
    }

    /**
     * constructor for block.
     *
     * @param rect        - the rectangle.
     * @param imageFill   - the imge to fill the block.
     * @param colorFill   - the color to fill the block.
     * @param numOfHits   - the number of hits.
     * @param colorStroke - the color.
     */
    public Block(Rectangle rect, TreeMap<Integer, Image> imageFill, TreeMap<Integer, Color> colorFill,
                 Integer numOfHits, Color colorStroke) {
        this.rect = rect;
        this.imageFillMap = imageFill;
        this.colorsFillMap = colorFill;
        this.hits = numOfHits;
        hitListeners = new ArrayList<HitListener>();
        this.stroke = colorStroke;
    }

    /**
     * fill the block.
     *
     * @param surface - the surface.
     */
    private void fillForBlock(DrawSurface surface) {
        if (this.colorsFillMap == null && this.imageFillMap == null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        } else if (this.colorsFillMap != null && this.colorsFillMap.containsKey(this.hits)) {
            if (this.colorsFillMap.get(this.hits) != null) {
                surface.setColor(this.colorsFillMap.get(this.hits));
                surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                        (int) this.rect.getWidth(), (int) this.rect.getHeight());
            }
        } else if (this.imageFillMap.containsKey(this.hits)) {
            surface.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    this.imageFillMap.get(this.hits));
        } else {
            if (this.colorsFillMap != null && this.colorsFillMap.containsKey(0)) {
                //System.out.println(this.colorsFillMap.get(0));
                if (this.colorsFillMap.get(0) != null) {
                    surface.setColor(this.colorsFillMap.get(0));
                    surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                            (int) this.rect.getWidth(), (int) this.rect.getHeight());
                }
            } else {
                surface.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                        this.imageFillMap.get(0));
            }
        }
    }

    /**
     * the function get the rectangle.
     *
     * @return rect the shape of the ball.
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * the function change the position and direction of the ball.
     *
     * @param collisionPoint  the point of th collision.
     * @param currentVelocity the current velocity.
     * @param hitter          the ball hitter.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        double startX = this.rect.getUpperLeft().getX();
        double startY = this.rect.getUpperLeft().getY();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        if (collisionPoint.equals(this.rect.getUpperLeft()) || collisionPoint.equals(this.rect.getRightUpperPoint())
                || collisionPoint.equals(this.rect.getLeftLowerPoint())
                || collisionPoint.equals(this.rect.getRightLowerPoint())) {
            newVelocity.setDx(currentVelocity.getDX() * (-1));
            newVelocity.setDy(currentVelocity.getDY() * (-1));
            hits--;
        } else if ((x > startX) && (x < startX + this.rect.getWidth())) {
            newVelocity.setDy((currentVelocity.getDY()) * (-1));
            hits--;
        } else if ((y > startY) && (y < startY + this.rect.getHeight())) {
            newVelocity.setDx(currentVelocity.getDX() * (-1));
            hits--;
        } else {
            newVelocity.setDy((currentVelocity.getDY()) * (-1));
            hits--;
        }
        if (hits <= 0) {
            hits = 0;
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * the function draws the block.
     *
     * @param surface the draw surface.
     */
    public void drawOn(DrawSurface surface) {
        try {
            fillForBlock(surface);
            if (this.stroke != null) {
                surface.setColor(this.stroke);
                surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                        (int) this.getCollisionRectangle().getUpperLeft().getY(),
                        (int) this.getCollisionRectangle().getWidth(),
                        (int) this.getCollisionRectangle().getHeight());
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
     * list of sprites of the game.
     *
     * @param g the game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable((Collidable) this);
        g.addSprite((Sprite) this);
    }

    /**
     * the function add remove sprite and collidable items from the
     * list of sprites of the game.
     *
     * @param game the gameLevel object.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
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
     * get the hit points.
     *
     * @return the hit points.
     */
    public int getHitPoints() {
        return hits;
    }
}
