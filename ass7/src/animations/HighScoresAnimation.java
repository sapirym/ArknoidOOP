package animations;

import biuoop.DrawSurface;
import playgame.HighScoresTable;

import java.awt.Color;

/**
 * HighScoresAnimation class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class HighScoresAnimation implements Animation {
    private boolean stop = false;
    private HighScoresTable highScoresTable;

    /**
     * the constructor for CountdownAnimation.
     *
     * @param scores - the scores.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScoresTable = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLUE);
        d.drawText(d.getWidth() / 12, d.getHeight() * 1 / 3 - 50, "high scores", 50);
        d.setColor(Color.red);
        d.drawText(d.getWidth() / 12, d.getHeight() / 2 - 50
                , "name", 32);
        d.drawText(d.getWidth() / 12 + 100, d.getHeight() / 2 - 50
                , "score", 32);
        d.setColor(Color.black);
        if (!highScoresTable.getHighScores().isEmpty()) {
            for (int i = 0; i < highScoresTable.getHighScores().size(); i++) {
                for (int j = 1; j < 2; j++) {
                    d.drawText(d.getWidth() / 12, d.getHeight() / 2 + i * 50
                            , highScoresTable.getHighScores().get(i).getName(), 32);
                    d.drawText(d.getWidth() / 12 + j * 100, d.getHeight() / 2 + i * 50
                            , Integer.toString(highScoresTable.getHighScores().get(i).getScores()), 32);
                }
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
