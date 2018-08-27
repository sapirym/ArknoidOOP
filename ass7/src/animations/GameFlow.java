package animations;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.GameLevel;
import playgame.ScoreInfo;
import listeners.Counter;
import levels.LevelInformation;

import java.io.File;
import java.io.IOException;


import playgame.HighScoresTable;
import levels.SpaceInvaders;

/**
 * GameFlow class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private Counter livesCounter;
    private boolean win = false;
    private Counter scoreCounter;
    private HighScoresTable highScoresTab;
    private File file;
    private Counter levelNum;

    /**
     * The constructor for GameFlow.
     *
     * @param ar - animation runner object for running each level
     * @param ks - KeyboardSensor object for knowing the user acts
     * @param levelParm - the level to [lay on
     * @param highScoresTab - the high scores table object
     * @param file - the file that save the scores table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, LevelInformation levelParm,
                    HighScoresTable highScoresTab, File file) {
        this.animationRunner = ar;
        this.keyboard = ks;
        this.livesCounter = new Counter(3);
        this.scoreCounter = new Counter(0);
        this.levelNum = new Counter(1);
        this.highScoresTab = highScoresTab;
        this.file = file;
    }

    /**
     * This method runs the spaceInvaderLevel one by one and the
     * end screen in the end of the playgame.
     * @param spaceInvaderLevel list of the spaceInvader.
     */
    public void runLevels(LevelInformation spaceInvaderLevel) {
        double initialSpeed = 80;
        GameLevel level = new GameLevel(spaceInvaderLevel,
                this.keyboard,
                this.animationRunner, this.scoreCounter, this.livesCounter, this.levelNum, initialSpeed);
        level.initialize();
        while (level.getRemainingBlocksNum() >= 0 && this.livesCounter.getValue() > 0) {
            if (level.getRemainingBlocksNum() == 0) {
                spaceInvaderLevel = new SpaceInvaders();
                this.levelNum.increase(1);
                level = new GameLevel(spaceInvaderLevel,
                        this.keyboard,
                        this.animationRunner, this.scoreCounter,
                        this.livesCounter, this.levelNum, initialSpeed * 1.1);
                level.initialize();
            }
            level.playOneTurn();
        }
        if (this.livesCounter.getValue() == 0) {
            if (level.getRemainingBlocksNum() > 0) {
                win = false;
            }
        }
        if (this.livesCounter.getValue() > 0) {
            win = true;
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGui()
                .getKeyboardSensor(), KeyboardSensor.SPACE_KEY, new EndScreen(win, this.scoreCounter
                , this.animationRunner.getGui().getKeyboardSensor())));
        if (highScoresTab.getRank(this.scoreCounter.getValue()) <= this.highScoresTab.size()) {
            DialogManager dialog = this.animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo newOne = new ScoreInfo(this.scoreCounter.getValue(), name);
            this.highScoresTab.add(newOne);
            try {
                this.highScoresTab.save(this.file);
            } catch (IOException e) {
                System.out.print("wrong file!!!");
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, (new HighScoresAnimation(this.highScoresTab))));
        return;
    }
}
