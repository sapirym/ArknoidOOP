package levels;

import graphics.Block;
import graphics.Velocity;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * PrepareLevelInformation class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class PrepareLevelInformation {
    private List<String> level;
    private TreeMap<String, String> map;
    private BlocksFromSymbolsFactory factory;

    /**
     * constructor for PrepareLevelInformation.
     * @param levelString - the list of levels strings.
     */
    public PrepareLevelInformation(List<String> levelString) {
        this.level = new ArrayList<String>();
        this.map = new TreeMap<String, String>();
        this.level = levelString;
        String line;
        for (int i = 0; i < level.size(); i++) {
            if (!level.get(i).contains(":")) {
                break;
            }
            String[] split = level.get(i).trim().split(":");
            this.map.put(split[0].trim(), split[1].trim());
        }
        setBlocks();
    }

    /**
     * return the level information.
     * @return the level information.
     */
    public LevelInformation getLevelInformation() {
        Image image = null;
        List<Velocity> intiallBallsVeloc = makeBallsVelocities();
        int numOfBalls = makeBallsVelocities().size();
        String levelName = this.map.get("level_name");
        int paddleSpeed = Integer.parseInt(this.map.get("paddle_speed"));
        int paddleWidth = Integer.parseInt(this.map.get("paddle_width"));
        int numOfBlocks = Integer.parseInt(this.map.get("num_blocks"));
        List<Block> blockList = blocksForLevel();
        String imageString = this.map.get("background");
        String backgrounsString = this.map.get("background");
        boolean isImage =  imageString.contains("image");
        if (isImage) {
            try {
                backgrounsString = backgrounsString.replaceAll("[()]", "");
                backgrounsString = backgrounsString.substring(5);
                InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream(backgrounsString);
                Level levelInformation = new Level(numOfBalls,
                        intiallBallsVeloc, paddleSpeed, paddleWidth,
                        levelName, blockList, numOfBlocks);
                image = ImageIO.read(imageIS);
                levelInformation.setLevelBackground(image);
                imageIS.close();
                return levelInformation;
            } catch (IOException e) {
                System.err.println("Cannot read background image");
                e.printStackTrace();
            }
        }
        Level levelInformation = new Level(numOfBalls,
                intiallBallsVeloc, paddleSpeed, paddleWidth,
                levelName, blockList, numOfBlocks);
        levelInformation.setLevelBackground(new ColorsParser().colorFromString(backgrounsString));
        return levelInformation;
    }

    /**
     * return the level information.
     *
     * @return the level information.
     */
    private List<Velocity> makeBallsVelocities() {
        String velocitis = this.map.get("ball_velocities");
        String[] afterSplit, strings = velocitis.split("\\s+");
        List<Velocity> ballsVelocityList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            afterSplit = strings[i].split(",");
            ballsVelocityList.add(new Velocity(Integer.parseInt(afterSplit[0]), Integer.parseInt(afterSplit[1])));
        }
        return ballsVelocityList;
    }

    /**
     * Method to add block definitions to block factory.
     */
    private void setBlocks() {
        InputStream inputStream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(this.map.get("block_definitions"));
        BlocksDefinitionReader defReader = new BlocksDefinitionReader();
        this.factory = defReader.fromReader(new InputStreamReader(inputStream));
    }

    /**
     * make the blocks.
     *
     * @return list of blocks.
     */
    private List<Block> blocksForLevel() {
        ArrayList<String> listOfBlocksAndSpacersSymbols = new ArrayList<String>();
        String[] sArray;
        boolean runBlocks = false;
        for (int i = 0; i < this.level.size(); i++) {
            if (this.level.get(i).startsWith("END_BLOCKS")) {
                runBlocks = false;
            }
            if (runBlocks) {
                listOfBlocksAndSpacersSymbols.add(this.level.get(i));
            }
            if (this.level.get(i).startsWith("END_BLOCKS")) {
                runBlocks = false;
            } else if (this.level.get(i).startsWith("START_BLOCKS")) {
                runBlocks = true;
            }
        }
        List<Block> blocksList = new ArrayList<Block>();
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = this.factory;
        int x = Integer.parseInt(this.map.get("blocks_start_x"));
        int y = Integer.parseInt(this.map.get("blocks_start_y"));
        for (int i = 0; i < listOfBlocksAndSpacersSymbols.size(); i++) {
            sArray = listOfBlocksAndSpacersSymbols.get(i).split("");
            for (int j = 0; j < sArray.length; j++) {
                if (sArray[j].equals("")) {
                    continue;
                }
                if (blocksFromSymbolsFactory.checkSpaceSymbol(sArray[j])) {
                    x += blocksFromSymbolsFactory.getSpaceWidth(sArray[j]);
                } else if (blocksFromSymbolsFactory.checkBlockSymbol(sArray[j])) {
                    blocksList.add(blocksFromSymbolsFactory.getBlock(sArray[j], x, y));
                    x += blocksFromSymbolsFactory.getBlock(sArray[j], x, y)
                            .getCollisionRectangle().getWidth();
                }
            }
            y += Integer.parseInt(this.map.get("row_height"));
            x = Integer.parseInt(this.map.get("blocks_start_x"));
        }
        return blocksList;
    }

}
