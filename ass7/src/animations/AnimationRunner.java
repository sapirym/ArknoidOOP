package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class AnimationRunner {
    private biuoop.Sleeper sleeper = new Sleeper();
    private GUI gui;
    private int height = 600;
    private int width = 800;
    private int framesPerSecond = 60;
    private double dt;

    /**
     * constructor for AnimationRunner.
     */
    public AnimationRunner() {
        this.gui = new GUI("Space Invaders", width, height);
        this.dt = ((double) 1 / (double) 60);
    }

    /**
     * This method returns the GUI.
     *
     * @return gui object
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * the method run the animation.
     *
     * @param animation - the animation that needs to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 700 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, 1 / (double) (framesPerSecond));
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
