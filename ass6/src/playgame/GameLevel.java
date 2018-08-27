package playgame;

import animation.Animation;
import animation.AnimationRunner;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import counters.CountdownAnimation;
import counters.Counter;
import counters.ScoreIndicator;
import counters.ScoreTrackingListener;
import counters.LivesIndicator;
import graphics.Ball;
import graphics.Block;
import graphics.BallRemover;
import graphics.BlockRemover;
import graphics.Velocity;
import graphics.Paddle;
import graphics.Sprite;
import graphics.SpriteCollection;
import graphics.Collidable;
import levels.DrawLevelText;
import levels.LevelInformation;
import listeners.HitListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A GameLevel class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.Sleeper sleeper;
    private int width = 800;
    private int hight = 600;
    private Counter counterRemaining;
    private Counter counterScore;
    private Counter counterLives;
    private AnimationRunner runner;
    private boolean running = true;
    private biuoop.KeyboardSensor keyboard;
    private Paddle paddleGame;
    private LevelInformation levelInformation;
    private Counter counterBalls = new Counter(0);
    private int framesPerSecond;

    /**
     * constructor for Game.
     *
     * @param gui              the gui of the game.
     * @param levelInformation the current level of the game.
     * @param keyboard         the keyboard object.
     * @param runner           the animation runner.
     * @param lives            the counter of lives.
     * @param counterScore     the counter of the scores.
     * @param framesPerSecond  the frames per second.
     */
    public GameLevel(GUI gui, LevelInformation levelInformation, biuoop.KeyboardSensor keyboard
            , AnimationRunner runner, Counter lives, Counter counterScore, int framesPerSecond) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.counterLives = lives;
        this.framesPerSecond = framesPerSecond;
        this.counterRemaining = new Counter(0);
        this.keyboard = keyboard;
        this.runner = runner;
        this.levelInformation = levelInformation;
        this.counterRemaining = new Counter(this.levelInformation.numberOfBlocksToRemove());
        this.counterScore = counterScore;
    }

    /**
     * the function add collidable object to the list of collidables.
     *
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * the function add sprite object to the list of sprites.
     *
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * the function Initialize a new game: create the Blocks ,graphics.Ball and graphics.Paddle
     * and add them to the game.
     */
    public void initialize() {
        this.sleeper = new Sleeper();
        addSprite(this.levelInformation.getBackground());
        List<Block> blockList = this.levelInformation.blocks();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.counterScore);
        LivesIndicator livesIndicator = new LivesIndicator(this.counterLives);
        addSprite(scoreIndicator);
        addSprite(livesIndicator);
        for (Block item : blockList) {
            HitListener blockRemover = new BlockRemover(this, this.counterRemaining);
            HitListener scoreListener = new ScoreTrackingListener(counterScore);
            item.addHitListener(scoreListener);
            item.addHitListener(blockRemover);
            item.addToGame(this);
        }
        DrawLevelText drawLevelText = new DrawLevelText(this.levelInformation.levelName());
        addSprite(drawLevelText);
        createSafeLimit();
        gameFrame();
    }

    /**
     * the function create a safe zone, if the ball hits it, the ball will fall.
     */
    public void createSafeLimit() {
        graphics.Point point = new graphics.Point(0, hight - 25);
        graphics.Rectangle rectangle = new graphics.Rectangle(point, width, 20);
        Block blockLimit = new Block(rectangle, Color.BLACK, 1);
        blockLimit.addToGame(this);
        HitListener listener = new BallRemover(this, counterBalls);
        blockLimit.addHitListener(listener);
    }

    /**
     * the function makes balls and set the velocity.
     */
    public void createBalls() {
        List<Ball> balls = new ArrayList<Ball>();
        List<Velocity> velocityList = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            counterBalls.increase(1);
            balls.add(new Ball(width / 2, hight * 4 / 5 + 40, 5, Color.PINK,
                    new graphics.Point(0, 0), new graphics.Point(width, hight)));
            balls.get(i).setVelocity(Velocity.fromAngleAndSpeed(velocityList.get(i)
                    .getDX(), velocityList.get(i).getDY()));
            balls.get(i).setEnvironment(this.environment);
            balls.get(i).addToGame(this);
        }
    }

    /**
     * the function create the frame blocks of the game.
     */
    public void gameFrame() {
        Block upperBlock = new Block(new graphics.Rectangle(new graphics.Point(0, 30),
                this.width, 25), Color.GRAY, 1);
        upperBlock.addToGame(this);
        Block leftBlock = new Block(new graphics.Rectangle(new graphics.Point(0, 30),
                this.width / 30, this.hight * 29 / 30), Color.GRAY, 1);
        leftBlock.addToGame(this);
        Block lowerBlock = new Block(new graphics.Rectangle(new graphics.Point(this.width / 30, this.hight - 25),
                this.width * 14 / 15, this.hight / 30 + 25), Color.GRAY, 1);
        lowerBlock.addToGame(this);
        Block rightBlock = new Block(new graphics.Rectangle(new graphics.Point(this.width * 29 / 30, 30),
                this.width / 30, this.hight * 29 / 30), Color.GRAY, 1);
        rightBlock.addToGame(this);
    }

    /**
     * the function play one turn of the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.running = true;
        this.createBalls(); // or a similar method
        this.paddleGame = createPaddle();
        this.runner.run(new CountdownAnimation(3, 3, sprites)); // countdown before turn starts.
        this.runner.run(this);
    }

    /**
     * the function make the loop stop.
     *
     * @return boolean value, true if should stop, false if not.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * the function create a new paddle.
     *
     * @return paddle object.
     */
    public Paddle createPaddle() {
        graphics.Point startLimit = new graphics.Point(0, 0);
        graphics.Point endLimit = new graphics.Point(width, hight);
        graphics.Rectangle rectPaddle = new graphics.Rectangle(new graphics
                .Point(width / 2 - this.levelInformation.paddleWidth() / 2
                , hight * 7 / 8), this.levelInformation.paddleWidth(), hight / 20);
        Paddle paddle = new Paddle(keyboard, rectPaddle, Color.BLACK, startLimit, endLimit
                , this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
        return paddle;
    }

    /**
     * the function do one frame of the game.
     *
     * @param d  - the draw surface.
     * @param dt - the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (counterRemaining.getValue() <= 0 && counterBalls.getValue() > 0) {
            this.removeSprite(paddleGame);
            this.removeCollidable(paddleGame);
            counterScore.increase(100);
            this.running = false;
            return;
        }
        if (counterBalls.getValue() <= 0 && counterRemaining.getValue() > 0) {
            this.removeSprite(paddleGame);
            this.removeCollidable(paddleGame);
            this.counterLives.decrease(1);
            this.running = false;
            return;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    /**
     * the function get the counter of blocks.
     *
     * @return counter object.
     */
    public Counter getCounterBlock() {
        return counterRemaining;
    }

    /**
     * the function remove collidable object from the list of collidables.
     *
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * the function remove sprite object from the list of sprite.
     *
     * @param s the sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
