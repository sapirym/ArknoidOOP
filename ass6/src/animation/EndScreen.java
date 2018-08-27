package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import counters.Counter;
import java.awt.Color;
import java.awt.Polygon;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class EndScreen implements Animation {
    private boolean win;
    private Counter score;
    private KeyboardSensor keyboardSensor;
    private boolean stop = false;

    /**
     * the constructor for CountdownAnimation.
     *
     * @param keyboardSensor - the key board sensor.
     * @param score          - the counter of the scores.
     * @param win            - tells if the gamer wi or loose.
     */
    public EndScreen(boolean win, Counter score, KeyboardSensor keyboardSensor) {
        this.win = win;
        this.score = score;
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * draws one frame of the game.
     *
     * @param d  - the draw surface.
     * @param dt - the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (win) {
            d.setColor(Color.yellow);
            /*for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    makeStars(15 + j * 100, -20 + i * 100, d);
                }
            }*/
            d.setColor(Color.BLACK);
            d.drawText(100, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 50);
            d.setColor(Color.PINK);
            d.drawText(100 + 4, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 50);
        } else {
            /*for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    drawHeart(20 + j * 100, 0 + i * 80, d);
                }
            }*/
            d.setColor(Color.BLACK);
            d.drawText(50, d.getHeight() / 2 + 20, "Game Over. Your score is " + score.getValue(), 50);
            d.setColor(Color.PINK);
            d.drawText(50 + 4, d.getHeight() / 2 + 20, "Game Over. Your score is " + score.getValue(), 50);
        }
    }

    /**
     * draws hearts.
     *
     * @param d - the draw surface.
     * @param x - the x position.
     * @param y - the y position.
     */
    public void drawHeart(int x, int y, DrawSurface d) {
        int[] xp = {x, x + 41, x + 20};
        int[] yp = {y + 14, y + 14, y + 30};
        d.setColor(Color.pink.darker());
        d.fillOval(x, y, 20, 20); //left circle
        d.fillOval(x + 20, y, 20, 20); //to cover middle spot
        d.fillOval(x + 13, y + 10, 10, 10); //right circle
        d.fillPolygon(new Polygon(xp, yp, xp.length)); //bottom triangle
    }

    /**
     * draws stars.
     *
     * @param d - the draw surface.
     * @param x - the x position.
     * @param y - the y position.
     */
    public void makeStars(int x, int y, DrawSurface d) {
        int[] xDot = {32 + x, 40 + x, 63 + x, 42 + x, 50 + x
                , 30 + x, 5 + x, 18 + x, x, 22 + x, 33 + x};
        int[] yDot = {38 + y, 62 + y, 65 + y, 80 + y, 105 + y, 85 + y
                , 102 + y, 75 + y, 58 + y, 60 + y, 38 + y};
        d.fillPolygon(new Polygon(xDot, yDot, 11));
        d.setColor(Color.BLACK);
        d.drawPolygon(new Polygon(xDot, yDot, 11));
        d.setColor(Color.YELLOW);
    }

    /**
     * the function indicates when the loop need to stop.
     *
     * @return a boolean answer.
     */
    public boolean shouldStop() {
        //return this.stop;
        return false;
    }
}
