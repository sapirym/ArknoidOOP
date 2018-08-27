package levels;

import graphics.Block;
import graphics.Velocity;
import graphics.Sprite;
import graphics.Point;
import graphics.Rectangle;
import spaceinvaders.Alien;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A SpaceInvaders class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class SpaceInvaders implements LevelInformation {
    private int widthPaddle = 80;
    private List<Block> shields = new ArrayList<>();
    private int speedPaddle = 550;
    private List<Alien> aliens = new ArrayList<>();

    @Override
    public int paddleWidth() {
        return widthPaddle;
    }

    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return speedPaddle;
    }

    @Override
    public String levelName() {
        return "Battle No. ";
    }

    @Override
    public Sprite getBackground() {
        return new BlackBackground();
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public List<Alien> aliens() {
        Image image = null;
        InputStream inputStream;
        try {
            double x = 50;
            double y = 50;
            inputStream = ClassLoader.getSystemClassLoader().
                    getResourceAsStream("block_images/enemy.png");
            image = ImageIO.read(inputStream);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    this.aliens.add(new Alien(new Rectangle(new Point(x, y)
                            , 40, 30), image, 1));
                    x += 50;
                }
                y += 40;
                x = 50;
            }
            inputStream.close();
        } catch (IOException e) {
            System.err.println("Couldn't read image");
            e.printStackTrace();
        } finally {
            return this.aliens;
        }
    }

    @Override
    public List<Block> blocksShield() {
        for (int i = 110; i < 610; i += 5) {
            for (int j = 500; j <= 510; j += 5) {
                Block block = new Block((new Rectangle((new Point(i, j)), 5, 5)), Color.cyan
                        , 1, Color.cyan);
                shields.add(block);
            }
            if (i == 220 || i == 410) {
                i += 80;
            }
        }
        return this.shields;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 50;
    }

    @Override
    public Point shieldStart() {
        return new Point(110, 500);
    }
}
