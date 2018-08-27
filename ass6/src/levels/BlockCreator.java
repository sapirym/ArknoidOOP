package levels;

import graphics.Block;

/**
 * BlockCreator class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos - the x position.
     * @param ypos - the y position.
     * @return the new block.
     */
    Block create(int xpos, int ypos);
}
