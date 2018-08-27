package listeners;

import graphics.Ball;
import graphics.Block;

/**
 * A HitListener class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the graphics.Ball that's doing the hitting.
     *
     * @param beingHit the block that being hit.
     * @param hitter   - the ball hitter.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
