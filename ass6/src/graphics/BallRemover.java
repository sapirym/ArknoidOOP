package graphics;

import counters.Counter;
import listeners.HitListener;
import playgame.GameLevel;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * the constructor remove ball from the game.
     *
     * @param game          the game object.
     * @param removedBlocks the blocks that removed.
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the graphics.Ball that's doing the hitting.
     *
     * @param beingHit the block that being hit.
     * @param hitter   - the ball hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}
