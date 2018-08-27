package counters;

import graphics.Ball;
import graphics.Block;
import listeners.HitListener;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor for ScoreTrackingListener.
     *
     * @param scoreCounter - the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the graphics.Ball that's doing the hitting.
     *
     * @param beingHit the block that being hit.
     * @param hitter   - the ball hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() <= 0) {
            currentScore.increase(10);
        }
        if (beingHit.getHitPoints() >= 0) {
            currentScore.increase(5);
        }
    }
}
