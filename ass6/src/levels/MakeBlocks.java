package levels;

import graphics.Block;
import graphics.Point;
import graphics.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MakeBlocks class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class MakeBlocks implements BlockCreator {
    private int blockWidth;
    private int blockHeight;
    private int hit;
    private TreeMap<Integer, String> fillBlock;
    private String symbol;
    private List<String> blockStrings;
    private TreeMap<String, String> mapBlocks;
    private TreeMap<Integer, Image> fillImage;
    private TreeMap<Integer, Color> fillColor;

    /**
     * constructor for MakeBlock.
     *
     * @param symbolOfBlock - symbol of block.
     * @param blocksList    - the list of blocks.
     */
    public MakeBlocks(String symbolOfBlock, List<String> blocksList) {
        symbol = symbolOfBlock;
        mapBlocks = new TreeMap<>();
        fillBlock = new TreeMap<>();
        fillImage = new TreeMap<>();
        fillColor = new TreeMap<>();
        blockStrings = blocksList;
        orderSymbol();
        this.blockWidth = Integer.parseInt(this.mapBlocks.get("width"));
        this.hit = Integer.parseInt(this.mapBlocks.get("hit_points"));
        this.blockHeight = Integer.parseInt(this.mapBlocks.get("height"));
        for (Map.Entry<String, String> entry : this.mapBlocks.entrySet()) {
            if (entry.getKey().toString().startsWith("fill-")) {
                this.fillBlock.put(Integer.parseInt(entry.getKey().toString()
                                .replaceAll("[A-Za-z-]", "")),
                        entry.getValue().toString());
            } else if (entry.getKey().toString().startsWith("fill")) {
                this.fillBlock.put(0, entry.getValue().toString());
            }
        }
    }

    /**
     * order the symbol.
     */
    public void orderSymbol() {
        String bdefSymbol = null;
        String str = null;
        for (int i = 0; i < blockStrings.size(); i++) {
            if (blockStrings.get(i).startsWith("bdef symbol:" + this.symbol)) {
                bdefSymbol = this.blockStrings.get(i);
                break;
            }
            if (blockStrings.get(i).startsWith("default")) {
                str = blockStrings.get(i);
            }
        }
        bdefSymbol = bdefSymbol.substring(14);
        String[] split = bdefSymbol.split(" ");
        for (String pair : split) {
            String[] strings = pair.split(":");
            this.mapBlocks.put(strings[0], strings[1]);
        }
        if (str != null) {
            str = str.substring(8);
            String[] defaultPairs;
            String[] defPairs = str.split("\\s+");
            for (int i = 0; i < defPairs.length; i++) {
                defaultPairs = defPairs[i].split(":");
                if (!this.mapBlocks.containsKey(defaultPairs[0])) {
                    this.mapBlocks.put(defaultPairs[0], defaultPairs[1]);
                }
            }
        }
    }

    /**
     * create the block.
     *
     * @param x - the x position.
     * @param y - the y position.
     * @return block - the new block.
     */
    public Block create(int x, int y) {
        ColorsParser colorParser = new ColorsParser();
        Color stroke = null;
        if (this.mapBlocks.get("stroke") != null) {
            stroke = colorParser.colorFromString(this.mapBlocks.get("stroke"));
        }
        Point point = new Point(x, y);
        Rectangle rect = new Rectangle(point, this.blockWidth, this.blockHeight);
        fill();
        return new Block(rect, this.fillImage, this.fillColor, this.hit, stroke);
    }

    /**
     * fill the block.
     */
    public void fill() {
        ColorsParser colorParser = new ColorsParser();
        String str = null;
        Image image = null;
        for (Map.Entry<Integer, String> entry : this.fillBlock.entrySet()) {
            if (entry.getValue().contains("image")) {
                str = entry.getValue();
                str = str.replaceAll("[()]", "");
                str = str.substring(5);
                try {
                    InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
                    image = ImageIO.read(inputStream);
                    this.fillImage.put(entry.getKey(), image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.fillColor.put(entry.getKey(), colorParser.colorFromString(entry.getValue()));
            }
        }
    }
}
