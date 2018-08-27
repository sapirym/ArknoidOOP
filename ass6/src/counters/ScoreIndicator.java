package counters;

import biuoop.DrawSurface;
import graphics.Sprite;

import java.awt.Color;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * the constructor for ScoreIndicator.
     *
     * @param counter - the counter of the scores.
     */
    public ScoreIndicator(Counter counter) {
        this.scoreCounter = counter;
    }

    /**
     * the function draw the sprite to the screen.
     *
     * @param surface draw surface.
     */
    public void drawOn(DrawSurface surface) {
        try {
            surface.setColor(Color.WHITE);
            surface.fillRectangle(0, 0, 800, 30);
            surface.setColor(Color.PINK);
            surface.drawText(350, 19, "Score: " + Integer.toString(scoreCounter.getValue()), 20);
            surface.setColor(Color.BLACK);
            surface.drawRectangle(0, 0, 800, 30);
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
