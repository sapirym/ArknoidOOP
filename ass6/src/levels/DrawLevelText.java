package levels;

import biuoop.DrawSurface;
import graphics.Sprite;

/**
 * A DrawLevelText class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class DrawLevelText implements Sprite {
    private String levelName;

    /**
     * constructor for DrawLevelText.
     *
     * @param levelname the level name.
     */
    public DrawLevelText(String levelname) {
        this.levelName = levelname;
    }

    /**
     * the method draw the level name.
     *
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        d.drawText(30, 19, "Level: " + levelName, 20);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt - the dt.
     */
    public void timePassed(double dt) {
        return;
    }
}
