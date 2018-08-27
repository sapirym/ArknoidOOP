package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop = false;
    private boolean isAlreadyPressed;
    private boolean ignore;

    /**
     * the constructor for KeyPressStoppableAnimation.
     *
     * @param sensor    - the key board sensor.
     * @param key       - the string key.
     * @param animation - the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.animation = animation;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
        this.ignore = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.isAlreadyPressed) {
            if (this.sensor.isPressed(this.key)) {
                this.ignore = true;
            }
            this.isAlreadyPressed = false;
        }
        animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(key)) {
            if (!this.ignore) {
                this.stop = true;
            }
        } else {
            this.ignore = false;
        }
    }
}
