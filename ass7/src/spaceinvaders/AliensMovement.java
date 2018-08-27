package spaceinvaders;

import biuoop.DrawSurface;
import graphics.Point;
import graphics.Rectangle;
import graphics.Sprite;
import graphics.Velocity;
import levels.GameLevel;
import playgame.GameEnvironment;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AliensMovement class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class AliensMovement implements Sprite {
    private int guiWidth = 800;
    private int startOfSheild;
    private List<Alien> allAliens;
    private ArrayList<ArrayList<Alien>> alienListOfLists = new ArrayList<ArrayList<Alien>>();
    private double dx;
    private double dy;

    /**
     * constructor for move aliens.
     * @param allAliens - list of aliens.
     * @param startOfSheild - start vale of the shield.
     * @param dx - the dx.
     */
    public AliensMovement(List<Alien> allAliens, int startOfSheild, double dx) {
        this.dx = dx;
        dy = 0;
        this.allAliens = allAliens;
        this.startOfSheild = startOfSheild;

    }

    /**
     * move the position of the aliens.
     *
     * @param dt - the dt.
     */
    public void moveAll(double dt) {
        double rectHeight;
        double rectWidth;
        double y;
        double x;
        for (int i = 0; i < this.allAliens.size(); i++) {
            if (this.allAliens.get(i).getRect().getRightUpperPoint().getX() + dx * dt <= guiWidth
                    && this.allAliens.get(i).getRect().getUpperLeft().getX() + dx * dt >= 0) {
                x = this.allAliens.get(i).getRect().getUpperLeft().getX() + dx * dt;
                y = this.allAliens.get(i).getRect().getUpperLeft().getY() + dy * dt;
                rectWidth = this.allAliens.get(i).getRect().getWidth();
                rectHeight = this.allAliens.get(i).getRect().getHeight();
                this.allAliens.get(i).setRectangle(new Rectangle(new Point(x, y), rectWidth, rectHeight));
            } else {
                this.dx *= -1.1;
                for (int j = 0; j < this.allAliens.size(); j++) {
                    rectWidth = this.allAliens.get(j).getCollisionRectangle().getWidth();
                    rectHeight = this.allAliens.get(j).getCollisionRectangle().getHeight();
                    x = this.allAliens.get(j).getCollisionRectangle().getUpperLeft().getX();
                    y = this.allAliens.get(j).getCollisionRectangle().getUpperLeft().getY();
                    Point point = new Point(x, y + 15);
                    this.allAliens.get(j).setRectangle(new Rectangle(point, rectWidth, rectHeight));
                }
                break;
            }
        }

    }

    /**
     * call to the moveAll.
     *
     * @param dt the dt.
     */
    public void timePassed(double dt) {
        this.moveAll(dt);
    }

    /**
     * don't do nothing.
     *
     * @param surface - the DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * order the aliens lists in columns.
     *
     * @return the last list of the columns.
     */
    private List<Alien> lastRow() {
        List<Alien> row = new ArrayList<>();
        for (int i = 0; i < this.alienListOfLists.size(); i++) {
            if (this.alienListOfLists.get(i).size() > 0) {
                row.add(this.alienListOfLists.get(i).get(0));
            }
        }
        return row;
    }

    /**
     * move the aliens to the start point.
     *
     * @param startSpeed - the initial speed.
     */
    public void backToStart(double startSpeed) {
        this.dx = startSpeed;
        for (Alien alien : allAliens) {
            alien.setRectangle(alien.getRectStart());
        }
    }

    /**
     * remove aliens from the playgame.
     *
     * @param alien the aline to remove.
     */
    public void removeAliens(Alien alien) {
        for (ArrayList<Alien> alienList : alienListOfLists) {
            alienList.remove(alien);
        }
        this.allAliens.remove(alien);
    }

    /**
     * the shots of the aliens.
     *
     * @param environment - GameEnvironment.
     * @return the shot.
     */
    public Shot shoot(GameEnvironment environment) {
        Random random = new Random();
        List<Alien> lastInColumns = this.lastRow();
        int randomChoice = random.nextInt(lastInColumns.size());
        Shot shot = new Shot(lastInColumns.get(randomChoice).getCollisionRectangle().getLeftLowerPoint()
                .getX() + 20, lastInColumns.get(randomChoice).getCollisionRectangle()
                .getLeftLowerPoint().getY() + 5, 4, Color.RED, true, environment);
        shot.setVelocity(Velocity.fromAngleAndSpeed(180, 400));
        return shot;
    }


    /**
     * create list of lists of aliens.
     */
    public void makeListOfLists() {
        ArrayList<Alien> alienscolums = new ArrayList<Alien>();
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 50; j += 10) {
                alienscolums.add(this.allAliens.get(j));
            }
            this.alienListOfLists.add(alienscolums);
            alienscolums = new ArrayList<Alien>();
        }
    }

    /**
     * check if the aliens arrive to the start of the shields.
     *
     * @return boolean value.
     */
    public boolean getToSheild() {
        List<Alien> lastAliens = lastRow();
        for (Alien alien : lastAliens) {
            if (alien.getRect().getLeftLowerPoint().getY() >= startOfSheild) {
                return false;
            }
        }
        return true;
    }
}