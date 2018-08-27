package playgame;

import animation.Animation;
import animation.AnimationRunner;

/**
 * ShowHiScoresTask class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * constructor.
     *
     * @param runner              - the runner.
     * @param highScoresAnimation - the animation.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * runs the task.
     *
     * @return the T.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}