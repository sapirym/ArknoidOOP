package playgame;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import counters.HighScoresTable;
import levels.LevelInformation;
import levels.LevelSet;
import levels.LevelSetReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-10
 */

public class Ass6Game {

    /**
     * the main function.
     *
     * @param args the arguments of the command line.
     */
    public static void main(String[] args) {
        String path;
        if (args.length == 0) {
            path = "level_sets.txt";
        } else {
            path = args[0];
        }
        GUI gui = new GUI("arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        File file = new File("highscores.txt");
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
        menu(gui, new AnimationRunner(gui, 60), gui.getKeyboardSensor()
                , file, scoresTable, path);
    }

    /**
     * runs the menu.
     *
     * @param gui             - the gui.
     * @param animationRunner - the animation runner.
     * @param keyboardSensor  -the keyboard sensor.
     * @param file            - the file.
     * @param highScoresTable - the high score
     * @param pathArgs - the args path.
     */
    public static void menu(GUI gui, AnimationRunner animationRunner, KeyboardSensor keyboardSensor
            , File file, HighScoresTable highScoresTable, String pathArgs) {
        Map<String, Boolean> mapKeys;
        while (true) {
            Menu<Task<Void>> subMenu = new MenuAnimation<>("ARKANOID", keyboardSensor, animationRunner);
            List<LevelSet> listOfSets = getLevelsSet(pathArgs);
            for (int i = 0; i < listOfSets.size(); i++) {
                List<LevelInformation> currentLevelList = listOfSets.get(i).getLevels();
                subMenu.addSelection(listOfSets.get(i).getName(), listOfSets.get(i).getKey(),
                        new Task<Void>() {
                            public Void run() {
                                GameFlow flow = new GameFlow(animationRunner, keyboardSensor
                                        , gui, 60, highScoresTable, file);
                                flow.runLevels(currentLevelList);
                                return null;
                            }
                        });
            }
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("menu", keyboardSensor, animationRunner);
            menu.addSubMenu("s", "start game", subMenu);
            menu.addSelection("h", "show high scores", new Task<Void>() {
                public Void run() {
                    animationRunner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor()
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
            animationRunner.run(menu);
            Task<Void> t = menu.getStatus();
            t.run();
        }
    }

    /**
     * get the level set.
     * @param path - the path.
     * @return levelSet object.
     */
    public static List<LevelSet> getLevelsSet(String path) {
        List<LevelSet> listOfSets;
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        listOfSets = new LevelSetReader().fromReader(new InputStreamReader(inputStream));
        try {
            inputStream.close();
        } catch (IOException e) {
            System.out.println("problem with level set");
        }
        return listOfSets;
    }
}
