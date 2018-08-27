package graphics;

import biuoop.DrawSurface;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-28
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
}
