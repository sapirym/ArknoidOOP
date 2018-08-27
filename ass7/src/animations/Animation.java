package animations;

import biuoop.DrawSurface;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-23
 */
public interface Animation {
    /**
     * draws one frame of the playgame.
     * @param d  - the draw surface.
     * @param dt - the dt.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * the function indicates when the loop need to stop.
     *
     * @return a boolean answer.
     */
    boolean shouldStop();
}
