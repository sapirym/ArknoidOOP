package playgame;

import biuoop.KeyboardSensor;
import animations.AnimationRunner;
import animations.MenuAnimation;
import animations.HighScoresAnimation;
import animations.GameFlow;
import animations.KeyPressStoppableAnimation;
import levels.LevelInformation;
import levels.SpaceInvaders;
import java.io.File;
import java.io.IOException;


/**
 * the main class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-10
 */
public class Ass7Game {
    /**
     * the main function.
     * @param args the arguments of the command line.
     */
    public static void main(String[] args) {
        final AnimationRunner runner = new AnimationRunner();
        final File file = new File("highscores.txt");
        HighScoresTable scores;
        if (file.exists()) {
            scores = HighScoresTable.loadFromFile(file);
        } else {
            scores = new HighScoresTable(5);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HighScoresTable scoresTable = scores;
        menu(runner, runner.getGui().getKeyboardSensor(), file, scoresTable);
    }


    /**
     * runs the menu.
     *
     * @param animationRunner - the animation runner.
     * @param keyboardSensor  -the keyboard sensor.
     * @param file - the file.
     * @param highScoresTable - the high score
     */
    public static void menu(AnimationRunner animationRunner, KeyboardSensor keyboardSensor
            , File file, HighScoresTable highScoresTable) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Space Invaders", keyboardSensor, animationRunner);
        menu.addSelection("s", "Start Game",
                new Task<Void>() {
                    @Override
                    /**
                     * This method is for runs the task.
                     */
                    public Void run() {
                        LevelInformation spaceInvader = new SpaceInvaders();
                        GameFlow flow = new GameFlow(animationRunner, keyboardSensor
                                , spaceInvader, highScoresTable, file);
                        flow.runLevels(spaceInvader);
                        return null;
                    }
                });
            menu.addSelection("h", "show high scores", new Task<Void>() {
                public Void run() {
                    animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor()
                            , KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable)));
                    return null;
                }
            });
            menu.addSelection("q", "quit", new Task<Void>() {
                public Void run() {
                    System.exit(0);
                    return null;
                }
            });
        while (true) {
            animationRunner.run(menu);
            Task<Void> t = menu.getStatus();
            t.run();
            menu.setStop(false);
        }
    }
}

