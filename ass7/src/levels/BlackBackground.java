package levels;

import biuoop.DrawSurface;
import graphics.Sprite;

import java.awt.Color;

/**
 * Created by lenovo on 23/06/2017.
 */
public class BlackBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
