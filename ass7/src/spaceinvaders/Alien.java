package spaceinvaders;

import levels.GameLevel;
import graphics.Block;
import graphics.Rectangle;
import java.awt.Image;


/**
 * An Alien Class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class Alien extends Block {
    private Rectangle rectStart;

    /**
     * constructor for Alien.
     * @param rect - rectangle of the alien.
     * @param img - image of the alien.
     * @param hits - number of hits.
     */
    public Alien(Rectangle rect, Image img, Integer hits) {
        super(rect, img, hits);
        this.rectStart = rect;
    }

    /**
     * get the start rect of alien.
     * @return the start rect of the alien.
     */
    public Rectangle getRectStart() {
        return this.rectStart;
    }
    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeAlien(this);
        super.removeFromGame(gameLevel);
    }
}
