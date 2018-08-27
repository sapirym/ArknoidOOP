package graphics;


import levels.GameLevel;
import listeners.Counter;
import listeners.HitListener;

/**
 * A BallRemover class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private int deathBlockHieght;

    /**
     * the constructor remove ball from the GameLevel.
     * @param game - the GameLevel object.
     * @param removedBlocks the blocks that removed.
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * the constructor remove ball from the GameLevel.
     * @param gameLevel - the game level.
     * @param deathBlockHeight - the death block.
     */
    public BallRemover(GameLevel gameLevel, int deathBlockHeight) {
        this.game = gameLevel;
        this.deathBlockHieght = deathBlockHeight;
        this.remainingBlocks = null;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the graphics.Ball that's doing the hitting.
     * @param beingHit the block that being hit.
     * @param hitter - the ball hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLine().getStart().getY() == this.deathBlockHieght) {
            if (this.remainingBlocks != null) {
                this.remainingBlocks.decrease(1);
            }
            hitter.removeFromGame(this.game);
        }
    }
}
