package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import playgame.Menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuAnimation class.
 *
 * @param <T> the task
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<String> choice;
    private List<String> choiceText;
    private List<T> returnVals;
    private List<Menu<T>> listSubs;
    private List<Boolean> subMenu;
    private T status;
    private KeyboardSensor keyboard;
    private boolean stop;
    private AnimationRunner ar;
    private String name;
    /**
     * constructor to MenuAnimation.
     * @param name - name.
     * @param keyboard - ks.
     * @param animationRunner - animation runner.
     *
     */
    public MenuAnimation(String name, KeyboardSensor keyboard, AnimationRunner animationRunner) {
        this.ar = animationRunner;
        this.keyboard = keyboard;
        this.stop = false;
        this.returnVals = new ArrayList<T>();
        this.choice = new ArrayList<String>();
        this.choiceText = new ArrayList<String>();
        this.listSubs = new ArrayList<Menu<T>>();
        this.subMenu = new ArrayList<>();
        this.name = name;
    }
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(70, 70, 70));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 130, 150));
        int x = d.getHeight() * 2 / 5;
        d.drawText(d.getWidth() * 1 / 3 - 25, x - 100, this.name, 70);
        d.setColor(new Color(255, 0, 127));
        for (int i = 0; i < this.choiceText.size(); i++) {
            d.drawText(d.getWidth() * 1 / 3, x, ("(" + this.choice.get(i) + ")"
                    + " " + this.choiceText.get(i)), 40);
            x = x + 50;
        }
        for (int i = 0; i < this.returnVals.size(); i++) {
            if (this.keyboard.isPressed(this.choice.get(i))) {
                if (!subMenu.get(i)) {
                    this.status = this.returnVals.get(i);
                } else {
                    Menu<T> sub = this.listSubs.get(0);
                    d.setColor(new Color(70, 70, 70));
                    d.fillRectangle(0, 0, 800, 600);
                    d.setColor(new Color(96, 96, 96));
                    d.fillRectangle(0, 200, 800, 200);
                    d.setColor(new Color(255, 0, 127));
                    d.drawText(150, 300, "are you ready?", 70);
                    ar.run(sub);
                    this.status = sub.getStatus();
                    this.stop = false;
                }
                this.stop = true;
                break;
            }
        }
    }
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMen) {
        this.subMenu.add(true);
        this.choiceText.add(message);
        this.choice.add(key);
        this.returnVals.add(null);
        this.listSubs.add(subMen);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.choice.add(key);
        this.choiceText.add(message);
        this.returnVals.add(returnVal);
        this.subMenu.add(false);
    }
    @Override
    public T getStatus() {
        return status;
    }
    @Override
    public void setStop(boolean newStatus) {
        this.stop = newStatus;
    }
}



