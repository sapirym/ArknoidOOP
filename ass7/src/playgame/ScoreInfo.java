package playgame;

/**
 * ScoreInfo class.
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-05-28
 */
public class ScoreInfo {
    private int scores;
    private String name;

    /**
     * constructor for ScoreInfo.
     *
     * @param name   - the name string.
     * @param scores - the scores.
     */
    public ScoreInfo(int scores, String name) {
        this.scores = scores;
        this.name = name;
    }

    /**
     * get the scores.
     *
     * @return scores - the scores.
     */
    public int getScores() {
        return scores;
    }

    /**
     * get the name.
     *
     * @return the name of the level.
     */
    public String getName() {
        return name;
    }
}
