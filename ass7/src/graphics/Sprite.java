package graphics;

import biuoop.DrawSurface;
import levels.GameLevel;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */

public interface Sprite {

    /**
     * the function draw the sprite to the screen.
     *
     * @param d draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt - the dt.
     */
    void timePassed(double dt);
    /**
     * add sprite t the playgame.
     *
     * @param g - the gameLevel object.
     */
    void addToGame(GameLevel g);
}
