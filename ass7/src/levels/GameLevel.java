package levels;

import animations.Animation;
import animations.AnimationRunner;
import animations.PauseScreen;
import animations.KeyPressStoppableAnimation;
import animations.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import playgame.GameEnvironment;
import graphics.Block;
import graphics.Rectangle;
import graphics.Point;
import graphics.Sprite;
import graphics.SpriteCollection;
import graphics.Ball;
import graphics.Paddle;
import graphics.Collidable;
import graphics.Velocity;
import graphics.BallRemover;
import graphics.BlockRemover;
import listeners.Counter;
import listeners.LivesIndicator;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
import spaceinvaders.Alien;
import spaceinvaders.AliensMovement;
import spaceinvaders.Shot;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A GameLevel class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-06-28
 */
public class GameLevel implements Animation {
    private LevelInformation levelInfo;
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private List<Ball> ballsList;
    private Paddle pad;
    private KeyboardSensor keyboard;
    private Counter blockCounter;
    private Counter score;
    private Counter numberOfLives;
    private List<Alien> alienList;
    private List<Block> blockList;
    private AliensMovement moveAliens;
    private double dtShootAlien = 0;
    private double dtShootPaddle = 0;
    private Counter levelNum;
    private double levelSpeed;
    private int height = 600;
    private int width = 800;
    private int widthBorder = 0;

    /**
     * Constructor for playgame level.
     *
     * @param levelInformation the parameter with the information about the level
     * @param ks               the parameter for the keyboard sensor
     * @param runner           the parameter how run the level
     * @param scoresCounter    the parameter how sum the score of the level
     * @param livesCounter     the parameter of player's lives.
     * @param numOfLevel       the number of the current level
     * @param speed            the Aliens's speed of the current level
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner runner,
                     Counter scoresCounter, Counter livesCounter, Counter numOfLevel, double speed) {
        this.ballsList = new ArrayList<Ball>();
        this.levelInfo = levelInformation;
        this.keyboard = ks;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(this.levelInfo.numberOfBlocksToRemove());
        this.score = scoresCounter;
        this.numberOfLives = livesCounter;
        this.levelNum = numOfLevel;
        this.runner = runner;
        running = true;
        this.alienList = levelInformation.aliens();
        this.blockList = levelInformation.blocksShield();
        this.levelSpeed = speed;
    }

    /**
     * This method is in charge of stopping condition.
     * @return True or False- stop or not.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Method to add collidable objects to environment.
     * @param c stands for a collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Method to update the linked list of the aliens
     * when one of the aliens remove from playgame.
     *
     * @param alien the alien to remove from the list
     */
    public void removeAlien(Alien alien) {
        this.moveAliens.removeAliens(alien);
    }

    /**
     * Method to add sprite.
     * @param s stands for sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Method to remove collidable objects from environment.
     * @param c stands for a collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Method to remove sprite.
     * @param s stands for sprite object
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method returns the value of the remaining blocksShield.
     * @return the value eof the remaining blocksShield.
     */
    public int getRemainingBlocksNum() {
        return this.blockCounter.getValue();
    }

    /**
     * Initialize method. This method initialize a new playgame: create the Blocks
     * and Ball (and Paddle) and add them to the playgame.
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInfo.getBackground());
        defineGameBorders();
        addBlocks();
        this.moveAliens = new AliensMovement(this.alienList, (int) (levelInfo.shieldStart().getY()), levelSpeed);
        this.moveAliens.makeListOfLists();
        this.moveAliens.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.numberOfLives);
        livesIndicator.addToGame(this);
    }

    /**
     * the function create the frame blocks of the playgame.
     */
    private void defineGameBorders() {
        Block lowerBlock = new Block(new Rectangle(new Point(0, height), width, 10),
                Color.black, null, Color.black);
        Block upperBlock = new Block(new Rectangle(new Point(0, 0), width, 30),
                Color.WHITE, null, Color.WHITE);
        BallRemover removerDown = new BallRemover(this, height);
        BallRemover removerUp = new BallRemover(this, 0);
        lowerBlock.addToGame(this);
        lowerBlock.addHitListener(removerDown);
        upperBlock.addToGame(this);
        upperBlock.addHitListener(removerUp);
    }

    /**
     * This method creates the paddle for each level.
     */
    private void createPaddle() {
        Rectangle rect = new Rectangle(new Point((400 - (this.levelInfo.paddleWidth() / 2)), height - 30)
                , this.levelInfo.paddleWidth(), 20);
        this.pad = new Paddle(keyboard, rect,  new Color(205, 133, 0), this.levelInfo.paddleSpeed(),
                this);
        this.pad.addToGame(this);
    }

    /**
     * This method removes the paddle from the playgame.
     */
    public void restart() {
        this.numberOfLives.decrease(1);
        this.removeSprite(this.pad);
        this.removeCollidable(this.pad);
        this.running = false;
        for (Ball ball : this.ballsList) {
            ball.removeFromGame(this);
        }
        ballsList.clear();
    }


    /**
     * the function do one frame of the playgame.
     * @param d - the draw surface.
     * @param dt - the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (!this.moveAliens.getToSheild()) {
            this.restart();
        }
        if (this.blockCounter.getValue() == 0) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        this.dtShootPaddle -= dt;
        this.dtShootAlien -= dt;
        if ((this.dtShootAlien <= 0)) {
            Shot ball = this.moveAliens.shoot(this.environment);
            ball.addToGame(this);
            this.ballsList.add(ball);
            this.dtShootAlien = 0.35;
        }
        d.setColor(Color.BLACK);
        d.drawText(100, 18, "Level Name: " + this.levelInfo.levelName() + this.levelNum.getValue(), 19);
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (this.dtShootPaddle <= 0) {
                Point position = new Point(this.pad.getCollisionRectangle().getUpperLeft().getX() + 40
                        , this.pad.getCollisionRectangle().getUpperLeft().getY() - 5);
                Ball ball = new Ball(position, 3, Color.WHITE, this.environment);
                ball.setVelocity(Velocity.fromAngleAndSpeed(0, 600));
                ball.addToGame(this);
                this.ballsList.add(ball);
                this.dtShootPaddle = 0.35;
            }
        } else if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY
                    , new PauseScreen(keyboard)));
        }
    }

    /**
     * This method runs the playgame- play one turn.
     */
    public void playOneTurn() {
        createPaddle();
        this.moveAliens.backToStart(this.levelSpeed);
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);

    }

    /**
     * add shield.
     */
    private void addBlocks() {
        ScoreTrackingListener trackingListener = new ScoreTrackingListener(this.score);
        BlockRemover blockRemove = new BlockRemover(this, this.blockCounter);
        for (int i = 0; i < this.alienList.size(); i++) {
            this.alienList.get(i).addToGame(this);
            this.alienList.get(i).addHitListener(trackingListener);
            this.alienList.get(i).addHitListener(blockRemove);
        }
        for (int i = 0; i < this.blockList.size(); i++) {
            this.blockList.get(i).addToGame(this);
            this.blockList.get(i).addHitListener(trackingListener);
            this.blockList.get(i).addHitListener(blockRemove);
        }
        Collections.reverse(this.alienList);
    }
}