package playgame;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import counters.Counter;
import counters.HighScoresTable;
import levels.LevelInformation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A GameFlow class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private int width = 800;
    private int height = 600;
    private GUI gui;
    private Counter lives = new Counter(7);
    private Counter scores = new Counter(0);
    private boolean win = false;
    private int framesPerSecond;
    private File file;
    private HighScoresTable highScoresTable;

    /**
     * constructor for GameFlow class.
     *
     * @param ar              - animation runner.
     * @param ks              - keyboard.
     * @param gui             - the gui.
     * @param framesPerSecond - the frames for a second.
     * @param highScoresTable - the high scores.
     * @param file            - the file.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, int framesPerSecond
            , HighScoresTable highScoresTable, File file) {
        this.ar = ar;
        this.ks = ks;
        this.width = 800;
        this.height = 600;
        this.gui = gui;
        this.win = false;
        this.framesPerSecond = framesPerSecond;
        this.file = file;
        this.highScoresTable = highScoresTable;
    }

    /**
     * the function runs the levels.
     *
     * @param levels - list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(gui, levelInfo, ks, ar, lives, scores, 60);
            level.initialize();
            while (level.getCounterBlock().getValue() > 0 && this.lives.getValue() > 0) {
                level.playOneTurn();
            }
            if (this.lives.getValue() == 0) {
                if (levelInfo.numberOfBlocksToRemove() > 0) {
                    win = false;
                }
                break;
            }
        }
        if (this.lives.getValue() > 0) {
            win = true;
        }
        this.ar.run(new KeyPressStoppableAnimation(ks
                , KeyboardSensor.SPACE_KEY, new EndScreen(win, this.scores, ks)));
        if (highScoresTable.getRank(this.scores.getValue()) <= this.highScoresTable.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo newOne = new ScoreInfo(this.scores.getValue(), name);
            this.highScoresTable.add(newOne);
            try {
                this.highScoresTable.save(this.file);
            } catch (IOException e) {
                System.out.print("wrong file!!!");
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(ks,
                KeyboardSensor.SPACE_KEY, (new HighScoresAnimation(this.highScoresTable))));
        return;
    }
}
