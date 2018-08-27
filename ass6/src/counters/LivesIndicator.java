package counters;

import biuoop.DrawSurface;
import graphics.Sprite;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class LivesIndicator implements Sprite {
    private Counter liveCounter;

    /**
     * the constructor for LivesIndicator.
     *
     * @param counter - the counter of the lives.
     */
    public LivesIndicator(Counter counter) {
        this.liveCounter = counter;
    }

    /**
     * the function draw the sprite to the screen.
     *
     * @param surface draw surface.
     */
    public void drawOn(DrawSurface surface) {
        try {
            surface.drawText(500, 19, "Lives: " + Integer.toString(liveCounter.getValue()), 20);
        } catch (Exception e) {
            System.out.println("Error: problem with 'surface'.");
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
