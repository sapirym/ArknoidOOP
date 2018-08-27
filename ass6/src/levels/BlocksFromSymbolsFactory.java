package levels;

import graphics.Block;

import java.util.Map;
import java.util.TreeMap;

/**
 * BlocksFromSymbolsFactory class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class BlocksFromSymbolsFactory {
    private Map<String, BlockCreator> creators;
    private Map<String, Integer> widthsOfSpacers;

    /**
     * constructor for BlocksFromSymbolsFactory.
     */
    public BlocksFromSymbolsFactory() {
        this.creators = new TreeMap<>();
        this.widthsOfSpacers = new TreeMap<>();
    }
    /**
     * check if it is block symbol.
     *
     * @param string - the string.
     * @return boolean value.
     */
    public boolean checkBlockSymbol(String string) {
        return this.creators.containsKey(string);
    }

    /**
     * check if it is space symbol.
     *
     * @param string - the string.
     * @return boolean value.
     */
    public boolean checkSpaceSymbol(String string) {
        return this.widthsOfSpacers.containsKey(string);
    }

    /**
     * add the spacer.
     * @param spacer - the spacer.
     * @param width  - the width.
     */
    public void addSpacer(String spacer, int width) {
        this.widthsOfSpacers.put(spacer, width);
    }

    /**
     * get the block.
     *
     * @param string - the string.
     * @param x - the x position.
     * @param y - the y position.
     * @return boolean value.
     */
    public Block getBlock(String string, int x, int y) {
        return creators.get(string).create(x, y);
    }

    /**
     * add the new block to the map.
     * @param symbol - the symbol.
     * @param creator - block creator.
     */
    public void addToBlockMap(String symbol, BlockCreator creator) {
        this.creators.put(symbol, creator);
    }

    /**
     * get the space width.
     * @param string - the string.
     * @return the space width.
     */
    public int getSpaceWidth(String string) {
        return this.widthsOfSpacers.get(string);
    }
}
