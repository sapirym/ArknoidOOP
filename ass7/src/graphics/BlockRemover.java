package graphics;

import levels.GameLevel;
import listeners.Counter;
import listeners.HitListener;

/**
 * This class removes the block from the playgame.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * This method removes the block from the playgame.
     *
     * @param gameLevel     GameLevel parameter
     * @param removedBlocks the counter of the removed blocksShield
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * This method checks if the value of the block is already 0
     * and calls to another method to removes it's from the playgame.
     *
     * @param beingHit the block that being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            if (hitter.shotFromAlien()) {
                if (!beingHit.alienType()) {
                    beingHit.removeFromGame(this.gameLevel);
                    beingHit.removeHitListener(this);
                    hitter.removeFromGame(this.gameLevel);
                }
            } else {
                beingHit.removeFromGame(this.gameLevel);
                beingHit.removeHitListener(this);
                hitter.removeFromGame(this.gameLevel);
                if (beingHit.alienType()) {
                    this.remainingBlocks.decrease(1);
                }
            }
        }
            /*if (!hitter.shotFromAlien()) {
                beingHit.removeFromGame(this.gameLevel);
                beingHit.removeHitListener(this);
                hitter.removeFromGame(this.gameLevel);
                if (beingHit.alienType()) {
                    this.remainingBlocks.decrease(1);
                }
            } else {
                if (!beingHit.alienType()) {
                    beingHit.removeFromGame(this.gameLevel);
                    beingHit.removeHitListener(this);
                    hitter.removeFromGame(this.gameLevel);
                }
            }*/


    }
}
