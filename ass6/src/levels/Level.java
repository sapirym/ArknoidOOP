package levels;

import graphics.Block;
import graphics.Sprite;
import graphics.Velocity;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

/**
 * Level class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class Level implements LevelInformation {
    private int numOfBalls;
    private List<Velocity> ballVelocity;
    private String levelName;
    private int paddleWidth;
    private int blocksToRemove;
    private int paddleSpeed;
    private List<Block> blockList;
    private Image image;
    private Color color;

    /**
     * Constructor.
     *
     * @param numBalls           number of balls in level
     * @param velocityList       List of balls velocities
     * @param paddleSpeed        paddle speed
     * @param paddleWidth        paddle width
     * @param levelName          level name
     * @param blocksList         list of blocks
     * @param numOfBlockToRemove number of blocks to remove from game
     */
    public Level(int numBalls, List<Velocity> velocityList, int paddleSpeed, int paddleWidth,
                 String levelName, List<Block> blocksList, int numOfBlockToRemove) {
        this.ballVelocity = velocityList;
        this.paddleSpeed = paddleSpeed;
        this.numOfBalls = numBalls;
        this.levelName = levelName;
        this.paddleWidth = paddleWidth;
        this.blockList = blocksList;
        this.blocksToRemove = numOfBlockToRemove;
    }

    /**
     * set the level background as image.
     *
     * @param img Background image
     */
    public void setLevelBackground(Image img) {
        this.color = null;
        this.image = img;
    }

    /**
     * set the level background as color.
     *
     * @param clr Background color.
     */
    public void setLevelBackground(Color clr) {
        this.image = null;
        this.color = clr;
    }

    /**
     * number of balls in game.
     *
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * The velocity of each ball.
     *
     * @return List of velocities for level of balls.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocity;
    }

    /**
     * return the speed of the paddle.
     *
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * return the paddle width.
     *
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * return the level name.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * set the background of the level.
     *
     * @return sprite item - the background.
     */
    public Sprite getBackground() {
        if (this.color == null) {
            return new LevelBackground(this.image);
        } else {
            return new LevelBackground(this.color);
        }
    }

    /**
     * blocks for level.
     *
     * @return blocks list.
     */
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * blocks to remove.
     *
     * @return number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }
}
