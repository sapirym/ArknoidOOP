package levels;

import biuoop.DrawSurface;
import graphics.Sprite;

import java.awt.Color;
import java.awt.Image;

/**
 * LevelBackground class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class LevelBackground implements Sprite {
    private Image image;
    private Color color;

    /**
     * constructor for the background.
     *
     * @param image -the image of the gui.
     */
    public LevelBackground(Image image) {
        this.image = image;
        this.color = null;
    }

    /**
     * constructor for the background.
     *
     * @param color color for background.
     */
    public LevelBackground(Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * Draw the background of the level.
     *
     * @param d - the draw surface.
     */
    public void drawOn(DrawSurface d) {
        if (this.image != null) {
            d.drawImage(0, 0, this.image);
        } else {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
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
