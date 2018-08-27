package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * the constructor for CountdownAnimation.
     *
     * @param k - the key board sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * draws one frame of the game.
     *
     * @param d - the draw surface.
     * @param dt - the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * the function indicates when the loop need to stop.
     *
     * @return a boolean answer.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
