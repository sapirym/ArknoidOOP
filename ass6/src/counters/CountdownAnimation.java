package counters;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import graphics.SpriteCollection;

import java.awt.Color;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom;
    private Long countPerNum;

    /**
     * the constructor for CountdownAnimation.
     *
     * @param numOfSeconds - the number of seconds to wait.
     * @param countFrom    - count from number.
     * @param gameScreen   - sprite collection.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        countPerNum = (long) ((this.numOfSeconds / this.countFrom) * 1000);
    }

    /**
     * draws one frame of the game.
     *
     * @param d  - the draw surface.
     * @param dt - the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLUE);
        d.drawText(40, d.getHeight() / 2, "the game will start in: " + Integer.toString(countFrom), 65);
        long start = System.currentTimeMillis();
        Sleeper sleeper = new Sleeper();
        long used = System.currentTimeMillis() - start;
        long left = this.countPerNum - used;
        if (left > 0) {
            sleeper.sleepFor(left);
        }
        this.countFrom--;
    }

    /**
     * the function indicates when the loop need to stop.
     *
     * @return a boolean answer.
     */
    public boolean shouldStop() {
        if (this.countFrom >= 0) {
            return false;
        }
        return true;
    }
}
