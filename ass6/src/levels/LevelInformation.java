package levels;


import graphics.Block;
import graphics.Sprite;
import graphics.Velocity;

import java.util.List;

/**
 * A LevelInformation class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public interface LevelInformation {
    /**
     * This method return a number of balls.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return list of velocities of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The initial velocity the paddle.
     *
     * @return the speed value.
     */
    int paddleSpeed();

    /**
     * The width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * the level name that will be displayed at the top of the screen.
     *
     * @return string- the leve name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return sprite object.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();
}
