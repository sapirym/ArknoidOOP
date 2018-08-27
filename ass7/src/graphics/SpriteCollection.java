package graphics;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-28
 */

public class SpriteCollection {
    private List<Sprite> spritesList = new ArrayList<Sprite>();

    /**
     * the function add the given sprites to the environment.
     *
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * the function call timePassed() on all sprites.
     *
     * @param dt - the dt.
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> newListSprites = new ArrayList<Sprite>(this.spritesList);
        if (!this.spritesList.isEmpty()) {
            for (Sprite sprite : newListSprites) {
                sprite.timePassed(dt);
            }
        }
    }

    /**
     * the function call drawOn(d) on all sprites.
     *
     * @param d the surface.
     */
    public void drawAllOn(DrawSurface d) {
        if (!this.spritesList.isEmpty()) {
            for (Sprite sprite : spritesList) {
                sprite.drawOn(d);
            }
        }
    }

    /**
     * the function remove sprite.
     *
     * @param sprite the sprite object.
     * @return the new list of sprites.
     */
    public List<Sprite> removeSprite(Sprite sprite) {
        if (!spritesList.isEmpty()) {
            if (this.spritesList.contains(sprite)) {
                this.spritesList.remove(sprite);
            }
        }
        return spritesList;
    }
}
