package spaceinvaders;

import playgame.GameEnvironment;
import graphics.Ball;

/**
 * Class Shot.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class Shot extends Ball {
    private boolean alienShot;

    /**
     * constructor for Shot.
     * @param x - x of the shot.
     * @param y - y of the shot.
     * @param r - radius of the shot.
     * @param alienShot - boolean value if the bullet shot from alien or not.
     * @param color - shot's color.
     * @param environment - GameEnvironment.
     */
    public Shot(double x, double y, int r, java.awt.Color color, boolean alienShot, GameEnvironment environment) {
        super(x, y, r, color, environment);
        this.setVelocity(0, 40);
        this.alienShot = !alienShot;
    }

    /**
     * check if the ball shot from alien.
     * @return boolean value.
     */
    public boolean shotFromAlien() {
        return this.alienShot;
    }
}
